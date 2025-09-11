package dev.di;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public class ContainerService {
  
  public static <T> T getObject(Class<T> classType) {
    
    // BookService에서 BookRepository의 인스턴스를 주입받기 위한 코드 시작
    T instance = createInstance(classType);
    Arrays.stream(classType.getDeclaredFields()).forEach(field -> {
      Inject annotation = field.getAnnotation(Inject.class); // 각각의 필드 중 @Inect Annotation이 붙어있는 필드가 있는지 확인
      if (annotation != null) {
        Object fieldInstance = createInstance(field.getType()); // BookService.java에 @Inject 필드가 작성된 필드의 타입은 BookRepository 타입
        field.setAccessible(true); // 필드가 public이 아닐 수 있기 때문에 private 필드여도 접근 가능하도록
        
        try {
          field.set(instance, fieldInstance); // BookRepository bookRepository; 필드의 값을 fieldInstance로 초기화
        } catch (IllegalArgumentException | IllegalAccessException e) {
          throw new RuntimeException(e);
        } 
      }
    });
    
    return instance; // BookService까지 테스트할 때 사용하는 코드
    // BookService에서 BookRepository의 인스턴스를 주입받기 위한 코드 끝
  
  }
  
  private static <T> T createInstance(Class<T> classType) {
    try {
      return classType.getConstructor(null).newInstance();
    } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
        | NoSuchMethodException | SecurityException e) {
      throw new RuntimeException(e);
    }
  }
}
