# 네트워킹이란?

- 네트워킹은 인터넷에 연결되어 있는 원격지의 서버 또는 원격지의 단말과 통신해서 데이터를 주고받는 동작들을 포함합니다. 이런 네트워킹은 여러분이 가지고 있는 단말의 데이터만 사용하는 것이 아니라 멀리 떨어져 있는 서버나 다른 사람의 단말의 데이터를 조회할 수 있습니다. 그리고 서버에 데이터를 저장할 때는 먼저 인터넷을 통해 데이터 통신이 가능한지를 알아본 후 데이터를 주고받는 과정도 진행합니다. 데이터를 주고받는 과정은 상당히 복잡합니다. 그래도 네트워킹을 사용하는 이유는 인터넷에 연결되어 있는 여러 단말을 동시에 사용할 수 있어서 다양한 데이터 자원을 효율적으로 사용할 수있기 때문입니다. 그러면 본격적으로 네트워크 연결 방식에 대해 알아보겠습니다.

## 네트워크 연결 방식 이해하기

- 원격지의 서버를 연결하는 가장 단순한 방식은 클라이언트와 서버가 일대일로 연결하는 '2-tier C/S(Client/Server)' 방식입니다.

![image1](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/10~11%EC%9D%BC%EC%B0%A8(6h)%20-%20%EC%8A%A4%EB%A0%88%EB%93%9C%EC%99%80%20%ED%95%B8%EB%93%A4%EB%9F%AC%2C%20HTTP%20%EC%86%8C%EC%BC%93%EC%9C%BC%EB%A1%9C%20%EC%9B%B9%20%ED%86%B5%EC%8B%A0%ED%95%98%EA%B8%B0%20%EB%93%B1/2.%20HTTP%20%EC%86%8C%EC%BC%93%EC%9C%BC%EB%A1%9C%20%EC%9B%B9%EA%B3%BC%20%ED%86%B5%EC%8B%A0%ED%95%98%EA%B8%B0(HttpURLConnection)/images/image1.pnghttps://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/10~11%EC%9D%BC%EC%B0%A8(6h)%20-%20%EC%8A%A4%EB%A0%88%EB%93%9C%EC%99%80%20%ED%95%B8%EB%93%A4%EB%9F%AC%2C%20HTTP%20%EC%86%8C%EC%BC%93%EC%9C%BC%EB%A1%9C%20%EC%9B%B9%20%ED%86%B5%EC%8B%A0%ED%95%98%EA%B8%B0%20%EB%93%B1/2.%20HTTP%20%EC%86%8C%EC%BC%93%EC%9C%BC%EB%A1%9C%20%EC%9B%B9%EA%B3%BC%20%ED%86%B5%EC%8B%A0%ED%95%98%EA%B8%B0(HttpURLConnection)/images/image1.png)

- '2-tier C/S' 연결 방식은 가장 많이 사용하는 네트워킹 방식이며 대부분 클라이언트가 서버에 연결되어 데이터를 요청하고 응답받는 단순한 개념으로 이해할 수 있습니다. 웹페이지를 볼 때 사용하는 HTTP 프로토콜, 파일전송을 위한 FTP 프로토콜 그리고 메일을 주고받는 POP3 프로토콜 등의 연결방식은 모두 위와 같은 방법으로 서버로 간편하게 접속하여 처리하는 것입니다.

![image2](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/10~11%EC%9D%BC%EC%B0%A8(6h)%20-%20%EC%8A%A4%EB%A0%88%EB%93%9C%EC%99%80%20%ED%95%B8%EB%93%A4%EB%9F%AC%2C%20HTTP%20%EC%86%8C%EC%BC%93%EC%9C%BC%EB%A1%9C%20%EC%9B%B9%20%ED%86%B5%EC%8B%A0%ED%95%98%EA%B8%B0%20%EB%93%B1/2.%20HTTP%20%EC%86%8C%EC%BC%93%EC%9C%BC%EB%A1%9C%20%EC%9B%B9%EA%B3%BC%20%ED%86%B5%EC%8B%A0%ED%95%98%EA%B8%B0(HttpURLConnection)/images/image1.pnghttps://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/10~11%EC%9D%BC%EC%B0%A8(6h)%20-%20%EC%8A%A4%EB%A0%88%EB%93%9C%EC%99%80%20%ED%95%B8%EB%93%A4%EB%9F%AC%2C%20HTTP%20%EC%86%8C%EC%BC%93%EC%9C%BC%EB%A1%9C%20%EC%9B%B9%20%ED%86%B5%EC%8B%A0%ED%95%98%EA%B8%B0%20%EB%93%B1/2.%20HTTP%20%EC%86%8C%EC%BC%93%EC%9C%BC%EB%A1%9C%20%EC%9B%B9%EA%B3%BC%20%ED%86%B5%EC%8B%A0%ED%95%98%EA%B8%B0(HttpURLConnection)/images/image2.png)

- 3-tier 연결 방식을 사용하면 서버를 좀 더 유연하게 구성할 수 있습니다. 또 응용 서버와 데이터 서버로 서버를 구성하면 데이터베이스를 분리할 수 있어 중간에 비즈니스 로직(Business Logic)을 처리하는응용 서버가 좀 더 다양한 역할을 할 수 있다는 장점이 생깁니다. 이것보다 좀 더 많은 단계들을 추가한 N-tier 연결 방식이 있지만 일반적으로는 3-tier까지만 이해해도 앱을 만드는 데 큰 무리가 없습니다.

- 단말 간의 통신이 일반화되며 클라이언트와 서버의 관계는 피어-투-피어(Peer-to-Peer) 통신으로 불리는 P2P 모델로도 변형되어 사용되기도 합니다. P2P 모델은 서버를 두지 않고 단말끼리 서버와 클라이언트 역할을 하죠. 이런 특징을 가지고 있는 P2P 모델은 정보 검색이나 파일 송수신으로 정보를 공유하는 데 많이 사용됩니다.

![image3](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/10~11%EC%9D%BC%EC%B0%A8(6h)%20-%20%EC%8A%A4%EB%A0%88%EB%93%9C%EC%99%80%20%ED%95%B8%EB%93%A4%EB%9F%AC%2C%20HTTP%20%EC%86%8C%EC%BC%93%EC%9C%BC%EB%A1%9C%20%EC%9B%B9%20%ED%86%B5%EC%8B%A0%ED%95%98%EA%B8%B0%20%EB%93%B1/2.%20HTTP%20%EC%86%8C%EC%BC%93%EC%9C%BC%EB%A1%9C%20%EC%9B%B9%EA%B3%BC%20%ED%86%B5%EC%8B%A0%ED%95%98%EA%B8%B0(HttpURLConnection)/images/image1.pnghttps://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/10~11%EC%9D%BC%EC%B0%A8(6h)%20-%20%EC%8A%A4%EB%A0%88%EB%93%9C%EC%99%80%20%ED%95%B8%EB%93%A4%EB%9F%AC%2C%20HTTP%20%EC%86%8C%EC%BC%93%EC%9C%BC%EB%A1%9C%20%EC%9B%B9%20%ED%86%B5%EC%8B%A0%ED%95%98%EA%B8%B0%20%EB%93%B1/2.%20HTTP%20%EC%86%8C%EC%BC%93%EC%9C%BC%EB%A1%9C%20%EC%9B%B9%EA%B3%BC%20%ED%86%B5%EC%8B%A0%ED%95%98%EA%B8%B0(HttpURLConnection)/images/image3.png)

- 우리가 흔히 사용하는 메신저 서비스나 인터넷 전화에 사용되는 SIP 프로토콜 기반의 서비스들은 서버가 있긴 하지만 P2P 모델의 특성을 가지고 있습니다. 그러므로 단말끼리의 통신 방식에 대한 기본적인이해도 필요합니다. 간단하게 네트워킹이 무엇인지 알아보았으니 가장 기초적인 네트워킹 방법인 소켓을 먼저 알아보겠습니다.

* * * 
# 소켓 사용하기

- 네트워킹을 이해하려면 먼저 TCP/IP 수준의 통신 방식을 제공하는 소켓이 무엇인지 알아야 합니다. IP주소로 목적지 호스트를 찾아내고 포트로 통신 접속점을 찾아내는 소켓 연결은 TCP와 UDP 방식으로나눌 수 있습니다. 하지만 일반적인 프로그래밍에서는 대부분 TCP 연결을 사용합니다. 따라서 앞으로 실습하는 내용은 모두 TCP 연결을 사용할 것입니다.


> 인터넷 전화에 많이 사용되는 SIP 프로토콜이나 멀티미디어 데이터 스트림을 처리하는 RTP 프로토콜은 기본적으로 UDP를 많이 사용합니다. 여기에서 SIP는 세션 개시 프로토콜(Session Initiation Protocol)이라고 하는데 IETF에서 정의한 시그널링 프로토콜입니다. 음성과 화상통화 같은 멀티미디어 세션을 제어하기 위해 널리 사용되고 있습니다.

## HTTP 프로토콜과 소켓

- HTTP 프로토콜은 소켓으로 웹 서버에 연결한 후에 요청을 전송하고 응답을 받은 다음 연결을 끊습니다. 이런 특성을 '비연결성(Stateless)'이라고 하는데 이런 특성 때문에 실시간으로 데이터를 처리하는 ﻿앱은 응답 속도를 높이기 위해 연결성이 있는 소켓 연결을 선호했습니다. 하지만 지금은 인터넷의 속도가 빨라져 HTTP 프로토콜을 사용하는 웹이 일반적이 되었고 결국 속도가 그렇게 느리지 않으면서도 국제 표준을 따를 수 있다는 장점을 가진 웹 서버로 많은 서버가 만들어지게 되었습니다.

- 안드로이드는 표준 자바에서 사용하던 java.net 패키지의 클래스들을 그대로 사용할 수 있습니다. 이 때문에 네트워킹의 기본이 되는 소켓 연결은 아주 쉽게 구현할 수 있습니다. 즉, 화면 구성을 위한 액티비티를 구성하고 나면 소켓 연결에 필요한 코드는 기존에 사용하던 자바 코드를 그대로 사용할 수 있습니다.

- 네트워킹을 실습하기 위한 프로젝트를 만들어보기 전에 먼저 알아두어야 할 것이 있습니다. 안드로이드는 소켓 연결 등을 시도하거나 응답을 받아 처리할 때 스레드를 사용해야 한다는 것입니다. 이전에는 권장사항이었으나 현재 플랫폼 버전에서는 강제사항이 되었으므로 스레드를 사용하지 않으면 네트워킹 기능 자체가 동작하지 않습니다.

> 원격지에 데이터를 요청하고 응답을 기다리는 네트워킹 기능은 네트워크의 상태에 따라 응답 시간이 길어질 수 있을뿐더러 최근 플랫폼에서는 스레드 사용을 강제하고 있기 때문에 이런 경우에는 UI 업데이트를 위해 핸들러를 사용합니다.




