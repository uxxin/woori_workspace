import React, { useState } from "react";
import { TODO_CATEGORY_ICON } from "@/constants/icon";
import IconButton from "@/components/ui/IconButton";
import TodoForm from "./TodoForm";
import { useTodosDispatch } from "@/contexts/TodoContext";
import NewModal from "@/components/ui/NewModal";
import TodoConfirm from "./TodoConfirm";

const TodoItem = ({ todo }) => {
  const dispatch = useTodosDispatch();
  //const [leftDate, setLeftDate] = useState(0);

  /**
   * 문제3. 등록된 할 일의 마감기한 표시 기능
   *
   * 아래의 함수는 문제3을 구현하기 위한 참고 가이드 라인이며,
   * 아래의 함수를 사용하지 않고, 자신만의 독자적인 로직으로 기능을 구현해도 무관합니다.
   *
   * 오늘 날짜부터 남은 마감기한을 계산하는 함수
   * 파라미터 명세 - @param {string} deadline - 마감기한(ex. 문자열값 '2025-07-29')
   * 반환값 명세 - @return {number} result - 오늘(현재 날짜)부터 마감기한 까지 남은 기간(ex. 3 -> 3일)
   */
  const calculateDeadline = (deadline) => {
    // 1. new Date()를 통해 현재 날짜를 구합니다.
    // const todayDate = new Date();
    // console.log(`오늘 날짜: ${todayDate}`);
    // // 2. new Date('날짜 값')을 통해 deadline의 날짜를 구합니다.
    // const deadlineDate = new Date(deadline);
    // console.log(`받아온 deadline 날짜: ${deadline}`);
    // // 3. 생성된 날짜 객체가 제공하는 getTime()을 통해 현재 날짜와 마감기한 각각의 밀리초(ms)를 구합니다.
    // // 4. 각 밀리초 간의 차를 계산합니다.
    // const leftDate = deadlineDate.getTime() - todayDate.getTime();
    // console.log(`남은 시간: ${leftDate}`);
    // // 5. 밀리초를 일(day)로 변환하기 위해 아래의 값으로 나눠줍니다.
    // // 연산식: (밀리초 간의 차) / (1000 * 60 * 60 * 24) + 1
    // leftDate = leftDate / (1000 * 60 * 60 * 24) + 1;
    // console.log(`남은시간 나눈거:${leftDate}`);
    // // 6. 소수점을 제거하기 위해 Math.floor()를 활용합니다.
    // // 7. 연산 결과 값을 반환합니다.
  };

  return (
    <li
      data-cy="todo-item"
      className="flex gap-4 justify-between my-4 py-4 px-4 border-[1px] bg-gray-700 rounded-md shadow-xl"
    >
      <div>
        <span className="text-lg font-medium text-gray-300">
          {TODO_CATEGORY_ICON[todo.category]}
        </span>

        {/* 문제3. 마감기한 표시용 기본 엘리먼트 */}
        <span className="text-gray-100">{` ${calculateDeadline(
          todo.deadline
        )}일 남음`}</span>
        <div>
          <h2
            data-test="title"
            className="mb-0 text-lg font-bold text-gray-100 uppercase"
          >
            {todo.title}
          </h2>
          <p className="mt-2 text-base text-gray-200">{todo.summary}</p>
        </div>
      </div>
      <div className="flex items-center gap-1">
        <NewModal>
          <NewModal.Open>
            <IconButton icon={"✏️"} />
          </NewModal.Open>
          <NewModal.Dialog>
            <TodoForm actionTitle={"수정"} todo={todo} />
          </NewModal.Dialog>
        </NewModal>

        <NewModal>
          <NewModal.Open>
            {/* 할일 삭제 */}
            <IconButton cy={"recycle-bin"} icon={"🗑"} />
          </NewModal.Open>
          <NewModal.Dialog>
            <TodoConfirm todo={todo} />
          </NewModal.Dialog>
        </NewModal>
      </div>
    </li>
  );
};
export default TodoItem;
