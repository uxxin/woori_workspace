import React, {
  cloneElement,
  createContext,
  useContext,
  useState,
} from "react";
import { createPortal } from "react-dom";

// Modal 창의 상태(open/close), 열고 닫는 함수, 세 가지를 관리하는 컨텍스트
const ModalContext = createContext();

const NewModal = ({ children }) => {
  const [isOpen, open] = useState(false);
  console.log(isOpen);

  const close = () => open(false);

  return (
    <ModalContext.Provider value={{ isOpen, open, close }}>
      {children}
    </ModalContext.Provider>
  );
};

const Open = ({ children }) => {
  const { open } = useContext(ModalContext);
  return cloneElement(children, { onClick: () => open(true) });
};

// JS는 함수도 객체로 취급
NewModal.Open = Open; // 함수 객체의 프로퍼티로 함수를 할당

// Dialog - 모달 윈도우
const Dialog = ({ children }) => {
  // TODO: Dialog가 수행할 수 있는 함수, 상태
  const { close, isOpen } = useContext(ModalContext);

  // 어떤 상태일 때 Modal을 활성화할 것인지?(조건부 렌더링)
  if (isOpen) {
    return createPortal();

    // Modal을 활성화할 때 사용하던 React의 API? createPortal
  } else {
    createPortal(
      <div
        onClick={close}
        data-cy="modal-backdrop"
        className="fixed top-0 left-0 w-full h-full backdrop-blur-md z-1"
      >
        <div className="fixed z-10 w-1/2 p-8 m-0 transform -translate-x-1/2 -translate-y-1/2 border-none rounded shadow-xl top-1/2 left-1/2 bg-slate-600">
          {cloneElement(children, { onClose: close })}
        </div>
      </div>,
      document.body
    );
    return null;
  }
};

// TODO: JS는 함수도 객체로 취급
NewModal.Dialog = Dialog;

export default NewModal;
