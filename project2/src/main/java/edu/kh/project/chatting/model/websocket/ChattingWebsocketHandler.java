package edu.kh.project.chatting.model.websocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.databind.ObjectMapper;

import edu.kh.project.chatting.model.vo.Message;

public class ChattingWebsocketHandler extends TextWebSocketHandler{

    private Logger logger = LoggerFactory.getLogger(ChattingWebsocketHandler.class);

    // 클라이언트와 연결이 완료되고 통신할 준비가 되면 수해되는 메서드
    // --> JS : new SockJS("/chattingSock");
    // --> servlet-context.xml에서 연결 
    // --> 핸들러 클래스 매핑되고
    //--> 해당 메서드 실행됨
    
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        // TODO Auto-generated method stub
        super.afterConnectionEstablished(session);
    }

    // 클라이언트로부터 텍스트메시ㅈ;를 받으면 수행되는 메서드
    // --> JS : chattingSock.send(JSON데이터);
    // --> HandleTextMessage 수행됨
    // --> 밑에 message,getPayLoad() == JSON데이터
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        
        
        // 전달받은 내용 확인(JSON 형식)
        logger.info("전달받은 ㅐㄴ용 : " + message.getPayload());
        
        // Jackson-databind에서 제공하는 ObjectMapper 객체 사용
        
        //ObjectMapper : JSON을 해석해서 지정된 VO로 변환하는 객체(필드에 값 세팅)
        
        ObjectMapper objectMapper  = new ObjectMapper();
        
        Message msg = objectMapper.readValue(message.getPayload(), Message.class);
                                        //JSON  , 변경할 VO클래스
        
    }
    // 클라이언트와 연결이 끊기면 수행되는 메서드
    //채팅방화면(SockJS 객체가 있는화면)을 벗어나면 연결 종료
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        // TODO Auto-generated method stub
        super.afterConnectionClosed(session, status);
    }
    
    
}


/* WebSocket
- 브라우저와 웹서버간의 전이중통신을 지원하는 프로토콜이다(양뱡향통신을 지원하는)
- HTML5버전부터 지원하는 기능이다.
- 자바 톰캣7버전부터 지원했으나 8버전부터 본격적으로 지원한다.
- spring4부터 웹소켓을 지원한다. 
(전이중 통신(Full Duplex): 두 대의 단말기가 데이터를 송수신하기 위해 동시에 각각 독립된 회선을 사용하는 통신 방식. 
대표적으로 전화망, 고속 데이터 통신)



WebSocketHandler 인터페이스 : 웹소켓을 위한 메소드를 지원하는 인터페이스
    -> WebSocketHandler 인터페이스를 상속받은 클래스를 이용해 웹소켓 기능을 구현


WebSocketHandler 주요 메소드
        
    void handlerMessage(WebSocketSession session, WebSocketMessage message)
    - 클라이언트로부터 메세지가 도착하면 실행
    
    void afterConnectionEstablished(WebSocketSession session)
    - 클라이언트와 연결이 완료되고, 통신할 준비가 되면 실행

    void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus)
    - 클라이언트와 연결이 종료되면 실행

    void handleTransportError(WebSocketSession session, Throwable exception)
    - 메세지 전송중 에러가 발생하면 실행 


----------------------------------------------------------------------------

TextWebSocketHandler :  WebSocketHandler 인터페이스를 상속받아 구현한 텍스트 메세지 전용 웹소켓 핸들러 클래스
 
    handlerTextMessage(WebSocketSession session, TextMessage message)
    - 클라이언트로부터 텍스트 메세지를 받았을때 실행
     

*/