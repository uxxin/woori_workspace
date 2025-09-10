package dev.security;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.logging.Logger;

@Controller
@RequestMapping("/transfer")
public class TransferController {

    private final Logger logger = Logger.getLogger(TransferController.class.getName());

    // transfer.html로 렌더링하는 코드
    @GetMapping
    public String showTransferForm() {
        return "transfer";
    }

    // 송금 처리 부분
    @PostMapping
    public String transfer(HttpServletRequest request) {
        String amount = request.getParameter("amount");
        String account = request.getParameter("account");

        // 송금처리가 발생한다고 가정
        logger.info(account + "계좌로" + amount + "원이 입금되었습니다.");

        return "home";
    }
}
