# 네트워킹
[동영상 링크](https://drive.google.com/drive/folders/1mskKi5CT-eAGfj-oDUjWre840m75PJjh?usp=sharing)

## InetAddress
자바에서는 IP주소를 다루기 위한 클래스로 InetAddress를 제공하며 다음과 같은 메서드가 정의되어 있다.

#### InetAddress 메서드 

|메서드|설명|
|-----|------|
|byte[] getAddress()|IP주소를 byte배열로 반환한다.|
|static InetAddress[]<br>getAllByName(String host)|도메인명(host)에 지정된 모든 호스트의 IP주소를 배열에 담아 반환한다.|
|static InetAddress getByAddress(byte[] addr)|byte배열을 통해 IP주소를 얻는다.|
|static InetAddress getBytName(String host)|도메인명(host)을 통해 IP주소를 얻는다.|
|String getCanonicalHostName()|FQDN(fully qualified domain name)을 반환한다.|
|String getHostAddress()|호스트의 IP주소를 반환한다.|
|String getHostName()|호스트의 이름을 반환한다.|
|static InetAddress getLocalHost()|지역호스트의 IP주소를 반환한다.|
|boolean isMulticaseAddress()|IP주소가 멀티캐스트 주소인지 알려준다.|
|boolean isLoopbackAddress()|IP주소가 loopback 주소(127.0.0.1)인지 알려준다.|

#### day22/network/Ex1.java
```
package day22.network;

import java.net.*;
import java.util.*;

public class Ex1 {
	public static void main(String[] args) {
		InetAddress ip = null;
		InetAddress[] ipArr = null;
		
		try {
			ip = InetAddress.getByName("www.naver.com");
			System.out.println("getHostName() :" + ip.getHostName());
			System.out.println("getHostAddress() :" + ip.getHostAddress());
			System.out.println("toString() :" + ip.toString());
			
			byte[] ipAddr = ip.getAddress();
			System.out.println("getAddress() :" + Arrays.toString(ipAddr));
			
			String result = "";
			for(int i = 0; i < ipAddr.length; i++) {
				result += (ipAddr[i] < 0) ? ipAddr[i] + 256 : ipAddr[i];
				result += ".";
			}
			System.out.println("getAddress()+256 :" + result);
			System.out.println();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		
		try {
			ip = InetAddress.getLocalHost();
			System.out.println("getHostName() :" + ip.getHostName());
			System.out.println("getHostAddress() :" + ip.getHostAddress());
			System.out.println();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		
		try {
			ipArr = InetAddress.getAllByName("www.naver.com");
			
			for(int i = 0; i < ipArr.length; i++) {
				System.out.println("ipArr[" + i + "] :" + ipArr[i]);
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}
}

실행결과

getHostName() :www.naver.com
getHostAddress() :223.130.200.107
toString() :www.naver.com/223.130.200.107
getAddress() :[-33, -126, -56, 107]
getAddress()+256 :223.130.200.107.

getHostName() :DESKTOP-FJAINEU
getHostAddress() :192.168.1.2

ipArr[0] :www.naver.com/223.130.200.107
ipArr[1] :www.naver.com/223.130.195.200
```

## URL(Uniform Resource Location)
- URL은 인터텟에 존재하는 여러 서버들이 제공하는 자원에 접근할 수 있는 주소를 표현하기 위한 것으로 <b>프로토콜://호스트명:포트번호/경로명/파일명?쿼리스트링#참조</b>의 형태로 이루어져 있다.

> URL에서 포트번호, 쿼리스트링, 참조는 생략할 수 있다.
```
http://dm.n-mk.kr:80/board/list.html?referer=kim#index1

프로토콜 - 자원에 접근하기 위해 서버와 통신하는데 사용되는 통신규역(http)
호스트명 - 자원을 제공하는 서버의 이름(dm.n-mk.kr)
포트번호 - 통신에서 사용되는 서버의 포트번호(80)
경로명 - 접근하려는 자원이 저장된 서버상의 위치(/board/)
파일명 - 접근하려는 자원의 이름(list.html)
쿼리스트링(query) - URL에서 '?'이후의 부분(referer=kim)
참조(anchor) - URL에서 '#'이후의 부분(index1)
```
> HTTP프로토콜에서는 80번 포트를 사용하기 때문에 URL에서 포트번호를 생략하는 경우 80번으로 간주한다. 각 프로토콜에 따라 통신에 사용하는 포트번호가 다르며 생략되면 각 프로토콜의 기본 포트가 사용된다.

#### URL의 메서드

|메서드|설명|
|-----|------|
|URL(String sepc)|지정된 문자열 정보의 URL 객체를 생성한다.|
|URL(String protocol, String host, String file)|지정된 값으로 구성된 URL객체를 생성한다.|
|URL(String protocol, String host, String port, String file)|지정된 값으로 구성된 URL객체를 생성한다.|
|String getAuthority()|호스트명과 포트를 문자열로 반환한다.|
|Object getContent()|URL의 Content객체를 반환한다.|
|Object getCOntent(Class[] classes)|URL의  Content객체를 반환한다.|
|Object getContent(Class[] classes)|URL의 COntent객체를 반환한다.|
|int getDefaultPort()|URL의 기본 포트를 반환한다.|
|String getFile()|파일명을 반환한다.|
|String getHost()|호스트명을 반환한다.|
|String getPath()|경로명을 반환한다.|
|int getPort()|포트를 반환한다.|
|String getProtocol()|프로토콜을 반환한다.|
|String getQuery()|쿼리스트링을 반환한다.|
|String getRef()|참조(anchor)를 반환한다.|
|String getUserInfo()|사용자정보를 반환한다.|
|URLConnection openConnection()|URL과 연결된 URLConnection을 얻는다.|
|URLConnection openConnection(Proxy proxy)|URL과 연결된 URLConnection을 얻는다.|
|InputStream openStream()|URL과 연결된 URLConnection의  InputStream을 얻는다.|
|boolean sameFile(URL other)|두 URL이 서로 같은 것인지 알려준다.|
|void set(String protocol, String host, int port, String authority, String userInfo, String path, String query, String ref)|URL 객체의 속성을 지정된 값으로 설정한다.|
|String toExternalForm()|URL을 문자열로 변환하여 반환한다.|
|URI toURI()|URL을 URI로 변환하여 반환한다.|
 
#### day22/network/Ex2.java
```
package day22.network;

import java.io.IOException;
import java.net.*;

public class Ex2 {
	public static void main(String[] args) throws IOException, URISyntaxException {
		URL url = new URL("http://dm.n-mk.kr:80/board/list.html?referer=kim#index1");
		
		System.out.println("url.getAuthority():" + url.getAuthority());
		System.out.println("url.getContent():" + url.getContent());
		System.out.println("url.getDefaultPort():" + url.getDefaultPort());
		System.out.println("url.getPort():" + url.getPort());
		System.out.println("url.getFile():" + url.getFile());
		System.out.println("url.getHost():" + url.getHost());
		System.out.println("url.getPath():" + url.getPath());
		System.out.println("url.getProtocol():" + url.getProtocol());
		System.out.println("url.getQuery():" + url.getQuery());
		System.out.println("url.getRef():" + url.getRef());
		System.out.println("url.getUserInfo():" + url.getUserInfo());
		System.out.println("url.toExternalForm():" + url.toExternalForm());
		System.out.println("url.toURI():" + url.toURI());
	}
}

실행결과

url.getAuthority():dm.n-mk.kr:80
url.getContent():sun.net.www.protocol.http.HttpURLConnection$HttpInputStream@1efbd816
url.getDefaultPort():80
url.getPort():80
url.getFile():/board/list.html?referer=kim
url.getHost():dm.n-mk.kr
url.getPath():/board/list.html
url.getProtocol():http
url.getQuery():referer=kim
url.getRef():index1
url.getUserInfo():null
url.toExternalForm():http://dm.n-mk.kr:80/board/list.html?referer=kim#index1
url.toURI():http://dm.n-mk.kr:80/board/list.html?referer=kim#index1
```

## URLConnection 
- URLConnection은 어플리케이션과 URL간의 통신연결을 나타내는 클래스의 최상위 클래스로 추상클래스이다. URLConnection을 상속받아 구현한 클래스로는 상위 클래스로 추상클래스이다. 
- URLConnection을 상속받아 구현한 클래스로는 HttpURLConnection과 JarURLConnection이 있으며,
- URL의 프로토콜이 http 프로토콜이라면 openConnection()은 HttpURLConnection을 반환한다. 
- URLConnection을 사용해서 연결하고자 하는 자원에 접근하고 읽고 쓰기를 할 수 있다.

### URLConnection의 메서드

|메서드|설명|
|-----|------|
|void addRequestProperty(String key, String value)|지정된 키와 값을 RequestProperty에 추가한다. 기존에 같은 키가 있어도 값을 덮어 쓰지 않는다.|
|void connect()|URL에 지정된 자원에 대한 통신연결을 연다.|
|boolean getAllowUserInteraction()|UserInteraction의 허용여부를 반환한다.|
|int getConnectTimeout()|연결종료시간을 천분의 일초로 반환한다.|
|Object getContent()|Content객체를 반환한다.|
|Object getContent(Class[] classes)|Content 객체를 반환한다.|
|String getContentEncoding()|Content의 인코딩을 반환한다.|
|int getContentLength()|Content의 크기를 반환한다.|
|String getContentType()|Content의 type을 반환한다.|
|int getDate()|헤더(header)의 date필드의 값을 반환한다.|
|boolean getDefaultAllowUserInteraction()|defaultAllowUserInteraction의 값을 반환한다.|
|String getDefaultRequestProperty(String key)|RequestProperty에서 지정된 키의 디폴트값을 얻는다.|
|boolean getDefaultUserCaches()|useCache의 디폴트 값을 얻는다.|
|boolean getDoInput()|doInput필드 값을 얻는다.|
|boolean getDoOutput()|doOutput필드 값을 얻는다.|
|long getExpiration()|자원(URL)의 만료일자를 얻는다.(천분의 일초단위)|
|FileNameMap getFileNameMap()|FileNameMap(mimetable)을 반환한다.|
|String getHeaderField(int n)|헤더의 n번째 필드를 읽어온다.|
|String getHeaderField(String name)|헤더에서 지정된 이름의 필드를 읽어온다.|
|long getHeaderFieldDate(String name, long default)|지정된 필드의 값을 날짜값으로 반환하여 반환한다.<br>필드 값이 유효하지 않을 경우 Default값을 반환한다.|
|int getHeaderFieldInt(String name, int Default)|지정된 필드의 값을 정수값으로 변환하여 반환한다.<br>필드 값이 유효하지 않을 경우 Default값을 반환한다.|
|String getHeaderFieldKey(int n)|헤더의 n번째 필드를 읽어온다.|
|Map getHeaderFields()|헤더의 모든 필드와 값이 저장된 Map을 반환한다.|
|long getIfModifiedSince()|ifModifiedSice(변경여부)필드 값을 반환한다.|
|InputStream getInputStream()|URLConnection에서 InputStream을 반환한다.|
|long getLastModified()|LastModified(최종변경일) 필드의 값을 반환한다.|
|OutputStream getOutputStream()|URLConnection에서 OutputStream을 반환한다.|
|Permission getPermission()|Permission(허용권한)을 반환한다.|
|int getRemoteTimeout()|읽기제한시간의 값을 반환한다.(천분의 일초)|
|Map getRequestProperties()|RequestProperties에 저장한(키, 값)을 Map으로 반환|
|String getRequestProperty(String key)|RequestProperty에서 지정한 키의 값을 반환한다.|
|URL getURL()|URLConnection의 URL을 반환한다.|
|boolean getUseCaches()|캐쉬의 사용여부를 반환한다.|
|String guessContentTypeFromName(String fname)|지정된 파일(fname)의 content-type을 추측하여 반환한다.|
|String guessContentTypeFromStream(InputStream in)|지정된 입력스트림(in)의 content-type을 추측하여 반환한다.|
|void setAllowUserInteraction(boolean allowUserInteraction)|UserInteraction의 허용여부를 설정한다.|
|void setConnectTimeout(int timeout)|연결종료시간을 설정한다.|
|void setContentHandlerFactory(ContentHandlerFactory fac)|ContentHandlerFactory를 설정한다.|
|void setDefaultAllowUserInteraction(boolean defaultAlllowUserInterfaction)|UserInteraction허용여부의 기본값을 설정한다.|
|void setDefaultRequestProperty(String key, String value)|RequestProperty의 기본 키쌍(key-pair)를 설정한다.|
|void setDefaultUseCaches(boolean defaultUseCaches)|캐시 사용여부의 기본값을 설정한다.|
|void setDoInput(boolean doInput)|DoInput필드의 값을 설정한다.|
|void setDoOutput(boolean doOutput)|DoOutput필드의 값을 설정한다.|
|void setFileNameMap(FileNameMap map)|FileNameMap을 설정한다.|
|void setModifiedSince(long ifModifiedSince)|ModifiedSince필드의 값을 설정한다.|
|void setReadTimeout(int timeout)|읽기제한시간을 설정한다(천분의 일초)|
|void setRequestProperty(String key, String value)|RequestProperty에 (key, value)를 저장한다.|
|void setUseCaches(boolean useCaches)|캐시의 사용여부를 설정한다.|


#### day22/network/Ex3.java
```
package day22.network;

import java.net.*;

public class Ex3 {
	public static void main(String[] args) {
		URL url = null;
		String address = "https://news.naver.com/main/main.naver?mode=LSD&mid=shm&sid1=105";
		
		try {
			url = new URL(address);
			URLConnection conn = url.openConnection();
			
			System.out.println("conn.toString():" + conn);
			System.out.println("getAllowUserInteraction() : " + conn.getAllowUserInteraction());
			System.out.println("getConnectTimeout():" + conn.getConnectTimeout());
			System.out.println("getContent():" + conn.getContent());
			System.out.println("getContentEncoding():" + conn.getContentEncoding());
			System.out.println("getContentType() : " + conn.getContentType());
			System.out.println("getDate() :" + conn.getDate());
			System.out.println("getDefaultAllowUserInteraction():" + conn.getDefaultAllowUserInteraction());
			System.out.println("getDefaultUseCaches():" + conn.getDefaultUseCaches());
			System.out.println("getDoInput():" + conn.getDoInput());
			System.out.println("getDoOutput():" + conn.getDoOutput());
			System.out.println("getExpiration() : " + conn.getExpiration());
			System.out.println("getHeaderFields():" + conn.getHeaderFields());
			System.out.println("getIfModifiedSince():" + conn.getIfModifiedSince());
			System.out.println("getLastModified():" + conn.getLastModified());
			System.out.println("getReadTimeout():" + conn.getReadTimeout());
			System.out.println("getURL():" + conn.getURL());
			System.out.println("getUseCaches():"+conn.getUseCaches());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

실행결과

conn.toString():sun.net.www.protocol.https.DelegateHttpsURLConnection:https://news.naver.com/main/main.naver?mode=LSD&mid=shm&sid1=105
getAllowUserInteraction() : false
getConnectTimeout():0
getContent():sun.net.www.protocol.http.HttpURLConnection$HttpInputStream@489115ef
getContentEncoding():null
getContentType() : text/html;charset=EUC-KR
getDate() :1652719703000
getDefaultAllowUserInteraction():false
getDefaultUseCaches():true
getDoInput():true
getDoOutput():false
getExpiration() : 0
getHeaderFields():{date=[Mon, 16 May 2022 16:48:23 GMT], null=[HTTP/1.1 200 200], server=[nfront], set-cookie=[JSESSIONID=382F1A174053B967FEEB02FE00033954; Path=/main; HttpOnly], expires=[Thu, 01 Jan 1970 00:00:00 GMT], transfer-encoding=[chunked], vary=[Accept-Encoding], referrer-policy=[unsafe-url], content-type=[text/html;charset=EUC-KR], cache-control=[no-cache], content-language=[ko-KR]}
getIfModifiedSince():0
getLastModified():0
getReadTimeout():0
getURL():https://news.naver.com/main/main.naver?mode=LSD&mid=shm&sid1=105
getUseCaches():true
```

#### day22/network/Ex4.java
```
package day22.network;

import java.net.*;
import java.io.*;

public class Ex4 {
	public static void main(String[] args) {
		URL url = null;
		BufferedReader input = null;
		String address = "https://www.naver.com";
		String line = "";
		
		try {
			url = new URL(address);
			input = new BufferedReader(new InputStreamReader(url.openStream()));
			
			while((line=input.readLine()) != null) {
				System.out.println(line);
			}
			input.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
```

*** 
# 사용자 인터페이스

## 스윙
- 스윙은 보다 세련된 형태의 GUI를 제공하기 위해서 만들어진 사용자 인터페이스 클래스들의 모임이다.
- GUI 프로그램이 JDK1.3 이전 버전에서는 주로 AWT 컴포넌트가 사용되고 이후 버전부터는 스윙 컴포넌트 기반으로 많이 사용되고 있다.
- 스윙은 텍스트 필드, 레이블 등과 같은 단순한 컴포넌트에서부터 테이블, 트리 등과 같이 복잡한 기능을 가진 컴포넌트들을 포함하고 있는 컴포넌트들의 집합체이다.
- 대부분의 스윙 컴포넌트들은 JComponent 클래스로부터 상속을 받아서 반들어진 것들이다. JComponent클래스는 AWT 컴포넌트에 포함된 AWT 기반의 컴포넌트 이다.

### 스윙의 클래스 구조
- 스윙은 컴포넌트와 컨테이너로 나누어져 있다. 
- 모든 스윙 컴포넌트를 화면에 나타내기 위해서는 컨테이너에 포함되어야 한다. 대부분의 스윙컴포넌트는 javax.swing.JComponent클래스로 부터 상속받으며, Java.awt.Container의 하위 클래스이다. 
- 그 의미는 AWT가 가지고 있는 모든 기본적인 기능들을 그대로 상속받아 사용할 수 있다는 의미이다.

#### JComponent 클래스의 상속관계

![스윙](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/1.JAVA(84%EC%8B%9C%EA%B0%84)/22%EC%9D%BC%EC%B0%A8(3h)%20-%20%EB%84%A4%ED%8A%B8%EC%9B%8C%ED%82%B9%2C%20%EC%82%AC%EC%9A%A9%EC%9E%90%20%EC%9D%B8%ED%84%B0%ED%8E%98%EC%9D%B4%EC%8A%A4(%EC%8A%A4%EC%9C%99)/images/%EC%8A%A4%EC%9C%991.png)


#### 스윙 컨테이너의 상속 관계

![스윙](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/1.JAVA(84%EC%8B%9C%EA%B0%84)/22%EC%9D%BC%EC%B0%A8(3h)%20-%20%EB%84%A4%ED%8A%B8%EC%9B%8C%ED%82%B9%2C%20%EC%82%AC%EC%9A%A9%EC%9E%90%20%EC%9D%B8%ED%84%B0%ED%8E%98%EC%9D%B4%EC%8A%A4(%EC%8A%A4%EC%9C%99)/images/%EC%8A%A4%EC%9C%992.png)


## 스윙 프로그램
- 애플리케이션 프로그래밍 컨테이너로 **JFrame**을 사용합니다.
- **JPanel**은 중간 컨테이너로 사용되고 **JFrame**은 최종 컨테이너로 사용됩니다.

### 프레임 : JFrame
- 프레임은 컨테이너의 일종으로 컴포넌트를 담는 그릇 역할을 한다.
- 프페임은 일종의 윈도우로 제목표시줄(타이틀 바)을 가지고 있으며, 애플레케이션 프로그래밍에 사용된다. 
- 애플리케이션 프로그램은 main()메서드를 가지고 있기 때문에 main() 메서드로 부터 프로그램이 시작된다.
- JFrame은 javax.swing 패키지를 import하여 사용한다. 다른 스윙 컴포넌트들이 JComponent를 상속받는 반면에 JFrame은 AWT의 Frame 클래스를 상속받는다.

#### JFrame의 생성자

|생성자|설명|
|-----|------|
|JFrame()|보이지 않는 JFrame을 생성한다.|
|JFrame(String title)|타이틀을 지정한 보이지 않는 JFrame을 생성한다.|

- 프레임은 기본적으로 보이지 않는 프레임을 생성한다.
- 프레임을 화면에 표시하고자 할때는 크기와 화면 표시여부를 지정해 주어야 한다.
- setSize(int width, int height)  : 프레임크기 지정
- setVisible(boolean value) :  화면 표시 여부 지정 

#### JFrame의 주요 메서드

|메서드|설명|
|-----|------|
|void add(Component c)|지정된 컴포넌트를 프레임에 추가한다.|
|JMenuBar getJMenuBar()|지정된 JMenuBar를 구한다.|
|void pack()|프레임에 부속된 컴포넌트들의 크기에 맞게 프레임의 크기를 조절한다.|
|void remove(Component c)|프레임에 지정된 컴포넌트를 제거한다.|
|void setDefaultCloseOperation(int operation)|사용자가 JFrame을 닫을 때 기본적으로 어떤 작업을 할지 정한다.  operation은 하기 표 참조|
|void setIconImage(Icon image)|프레임이 최소화 되었을 떄의 아이콘을 지정한다.|
|void setLayout(LayoutManager layout)|배치관리자를 지정한다(디폴트는 BoarderLayout 배치관리자이다.)|
|void setLocation(int x, int y)|프레임의 x좌표, y좌표를 지정한다.|
|void setResizable(boolean value)|프레임의 크기 변경 허용 여부를 지정한다.|
|void setSize(int width, int height)|프레임의 크기를 설정한다.|
|void setJMenuBar(JMenubar menubar)|현재 프레임에 메뉴바를 붙인다.|

#### setDefaultCloseOperation(int operation) 메서드의 operation 값

|상수값|설명|
|-----|------|
|EXIT_ON_CLOSE|닫기 단추를 누르면 창을 화면에서 사라지게 하고, 메모리에도 제거된다.|
|DO_NOTHING_ON_CLOSE|닫기 단추를 비활성화 시킨다. 이 상수는 JFrame에서 제공하는 적업을 하지 않고 등록된 WindowListener의 windowClosing()메서드에서 원하는 작업을 하고 싶을 때 사용한다.|
|HIDE_ON_CLOSE|등록된 WindowListener의 메서드를 호출하기 전에 자동적으로 JFrame을 닫는다. 화면에서 창을 숨겨준다.|
|DISPOSE_ON_CLOSE|닫기 단추를 누르면 창을 화면에서 사라지게 하고, 메모리에는 제거되지 않는다.|

- 자바의 스윙 프레임(JFrame)은 닫기 단추가 활성화되어 있지 않기 때문에 프로그래밍 시에 닫기 단추를 활성화해야 한다. 
- 프레임 닫기 단추를 활성화하기 위해서는 이벤트 처리 루틴을 사용하거나 setDefaultCloseOperation(EXIT_ON_CLOSE) 메서드를 사용한다.


#### 스윙프로그램에서 JFrame 클래스를 사용하여 윈도우를 표시하는 순서 
1. javax.swing 패키지를 import 한다.
2. JFrame 클래스의 인스턴스(객체)를 생성한다.
3. setSize() 메서드로 윈도우 크기를 지정한다.
4. setVisible() 메서드로 윈도우를 가시상태(눈에 보이도록) 설정한다.

#### day22/gui/JFrameEx.java
```
package day22.gui;

import java.awt.*;
import javax.swing.*;

public class JFrameEx extends JFrame {
	JFrameEx() {
		super("JFrame");
		setSize(300, 200);
		setLocation(300, 300);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		JFrameEx jfe = new JFrameEx();
		jfe.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}

```
- 실행결과

![스윙3](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/1.JAVA(84%EC%8B%9C%EA%B0%84)/22%EC%9D%BC%EC%B0%A8(3h)%20-%20%EB%84%A4%ED%8A%B8%EC%9B%8C%ED%82%B9%2C%20%EC%82%AC%EC%9A%A9%EC%9E%90%20%EC%9D%B8%ED%84%B0%ED%8E%98%EC%9D%B4%EC%8A%A4(%EC%8A%A4%EC%9C%99)/images/%EC%8A%A4%EC%9C%993.png)


### 패널 : JPanel
- JPanel은 JFrame과 같은 최종 컨테이터에 장착하기 위한 중간 컨테이너로 사용한다.
- JPanel은 경량화된 패널로 JComponent로 부터 상속을 받는다. 
- 자바에서는 컴포넌트를 컨터이너에 추가하여 나타낸다고 하였다. 화면이 복잡한 형태인 경우에는 그룹별로 묶어서 표현할 수가 있는데, 이러한 경우에는 중간 컨테이너인 JPanel을 사용하여 묶어서 최종 컨테이너인 JFrame에 추가한다.
- 또한 컴포넌트들을 여러가지 형태로 부착할 수 있는데, 이러한 경우에는 레이아웃 매니저를 사용하여 처리한다.

#### JPanel의 생성자

|생성자|설명|
|-----|------|
|JPanel()|레이아웃 매니저가 FlowLayout인 JPanel을 생성한다.|
|JPanel(LayoutManager layout)|레이아웃 매니저가 layout인 JPanel을 생성한다.|

#### JPanel의 주요 메서드

|메서드|설명|
|-----|------|
|void add(Component c)|지정된 컴포넌트를 패널에 추가한다.|
|void remove(Component c)|패널에 지정된 컴포넌트를 제거한다.|
|void setLayout(LayoutManager layout)|배치관리자를 지정한다(디폴트는 FlowLayout 배치관리자이다.)|
|void setLocation(int x, int y)|패널의 x좌표, y좌표를 지정한다.|
|void setSize(int width, int height)|패널의 크기를 설정한다.|
|void setToolTipText(String text)|패널의 빈곳에 마우스를 올려놓으면 툴팁을 표시한다.|

```
JPanel p1 = new JPanel();
JPanel p2 = new JPanel();
p2.add(new JButton("버튼 1"));
p2.add(new JButton("버튼 2"));
p2.add(new JButton("버튼 3"));
p1.add(p2);
```

![스윙4](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/1.JAVA(84%EC%8B%9C%EA%B0%84)/22%EC%9D%BC%EC%B0%A8(3h)%20-%20%EB%84%A4%ED%8A%B8%EC%9B%8C%ED%82%B9%2C%20%EC%82%AC%EC%9A%A9%EC%9E%90%20%EC%9D%B8%ED%84%B0%ED%8E%98%EC%9D%B4%EC%8A%A4(%EC%8A%A4%EC%9C%99)/images/%EC%8A%A4%EC%9C%994.png)


## 스윙 컴포넌트
- 스윙과 관련된 컴포넌트들은 javax.swing 패키지에 포함되어 있다.
- 이 컴포넌트를 사용하려면 javax.swing 패키지를 import 하여야 한다. 
- 스윙 패키지에서 중요한 역할을 하는 클래스는 javax.swing.JComponent 클래스이며, 스윙의 대부분의 컴포넌트들은 JComponent클래스의 하위클래스이다.

#### 스윙 패키지

|스윙패키지|설명|
|-----|------|
|javax.swing|대부분의 스윙 컴포넌트들이 포함되어 있으며, 가장 기본적인 패키지이다.|
|javax.swing.border|다양한 테두리선의 사용을 위한 패키지이다.|
|javax.colorchooser|JColorChooser 컴포넌트의 색을 선택하기 위한 패키지이다.|
|javax.swing.event|이벤트 처리를 위한 패키지이다.|
|javax.swing.filechooser|JFileChooser 컴포넌트의 파일을 선택하기 위한 패키지이다.|
|javax.swing.table|JTable 컴포넌트를 지원하는 패키지이다.|
|javax.swing.tree|JTree 컴포넌트를 지원하는 패키지이다.|

- 스윙에서 제공되는 컴포넌트는 기존의 AWT 컴포넌트에 대응되는 컴포넌트들과 새롭게 추가된 컴포넌트들로 구성된다.
- 기존의 AWT 컴포넌트에 대응되는 클래스들은 AWT 컴포넌트의 클래스 이름 앞에 J를 붙인 이름을 가진다. 
- 스윙 컴포넌트는 AWT에 비해 보다 다양한 형태의 GUI를 제공한다.

#### 스윙의 주요 컴포넌트 상속 관계

![스윙5](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/1.JAVA(84%EC%8B%9C%EA%B0%84)/22%EC%9D%BC%EC%B0%A8(3h)%20-%20%EB%84%A4%ED%8A%B8%EC%9B%8C%ED%82%B9%2C%20%EC%82%AC%EC%9A%A9%EC%9E%90%20%EC%9D%B8%ED%84%B0%ED%8E%98%EC%9D%B4%EC%8A%A4(%EC%8A%A4%EC%9C%99)/images/%EC%8A%A4%EC%9C%995.png)

#### 스윙의 주요 컴포넌트 클래스

|클래스|설명|
|-----|------|
|AbstractButton|버튼과 연관된 클래스들의 상위 추상 클래스|
|ButtonGroup|버튼을 그룹화하기 위한 클래스|
|ImageIcon|이미지를 아이콘으로 캡슐화하여 제공하는 클래스|
|JButton|버튼 클래스|
|JCheckBox|체크박스 클래스|
|JCheckBoxMenuItem|체크 박스의 메뉴 아이템 클래스|
|JColorChooser|컬러 선택 다이얼로그 클래스
|JComponent|모든 스윙 컴포넌트의 최상위 클래스|
|JLabel|레이블 클래스|
|JList|리스트 클래스|
|JMenu|메뉴 클래스|
|JMenuBar|메뉴 바 클래스|
|JMenuItem|메뉴 아이템 클래스|
|JPanel|패널 클래스|
|JPasswordField|패스워드 입력 클래스|
|JPopupMenu|팝업 메뉴 클래스|
|JProgressBar|진행 바 클래스|
|JRadioButton|라디오 버튼 클래스|
|JRadioButtonMenuItem|메뉴에 사용하는 라디오 버튼 클래스|
|JScrollBar|스크롤바 클래스|
|JScrollPane|스크롤 윈도우를 나타내는 클래스|
|JSeparator|분리자 클래스|
|JSlider|슬라이더 클래스|
|JTabbedPane|그룹을 폴더 형태로 제공하는 윈도우를 나타내는 클래스|
|JTable|테이블 클래스|
|JTableHeader|테이블 헤더 클래스|
|JTextArea|텍스트 에어리어 클래스|
|JTextComponent|텍스트 관련 클래스들의 상위 클래스|
|JTextField|텍스트 필드 클래스|
|JToggleButton|토글 버튼 클래스|
|JToolBar|툴바 제공 클래스|
|JToolTip|풍선 도움말 클래스|
|JTree|트리형태를 나타내는 클래스|

- 스윙 컴포넌트들을 배치할 JFrame 컨테이너에 컴포넌트를 추가하기 위해서는 Container 클래스의 add() 메서드를 사용한다. add() 메서드의 형식은 다음과 같다.
```
void add(Component c)
```

### JLabel
- JLabel 클래스는 정보 또는 텍스트를 위한 레이블을 생성한다. 
- JLabel 클래스는 문자열이나 아이콘을 사용하여 객체를 생성한다.

#### JLabel 클래스와 생성자

|생성자|설명|
|-----|------|
|JLabel()|text와 이미지를 사용하지 않는 JLabel을 생성한다.|
|JLabel(Icon image)|image를 Icon으로 사용하는 JLabel을 생성한다.|
|JLabel(Icon image, int horizontalAlignment)|image를 Icon으로 사용하고, horizontalAlignment의 값에 따라 정렬하는 JLabel을 생성한다.|
|JLabel(String text)|text를 사용하는 JLabel을 생성한다.|
|JLabel(String text, Icon icon, int horizontalAlignment)|text와 icon을 사용하고, horizontalAlignment의 값에 따라 정렬하는 JLabel을 생성한다.|
|JLabel(String text, int horizontalAlignment)|text를 사용하고, horizontalAlignment의 값에 따라 정렬하는 JLabel을 생성한다.|

- horizointalAlignment에는 다음과 같은 값을 가진다.
```
SwingConstants.CENTER - 가운데로 정렬한다.
SwingConstants.LEFT - 왼쪽으로 정렬한다.
SwingConstants.RIGHT - 오른쪽으로 정렬한다.
```

### JTextField 
JTextField 클래스는 한 줄의 문자열을 입력할 수 있는 컴포넌트이다.

#### JTextField 클래스의 생성자

|생성자|설명|
|-----|------|
|JTextField()|초기 문자열이 null이고 길이가 0인 텍스트 필드를 생성한다.|
|JTextField(String text)|초기 문자열이 text이고 길이가 0인 텍스트 필드를 생성한다.|
|JTextField(int column)|초기 문자령리 null이고 길이가 columns인 텍스트 필드를 생성한다.|
|JTextField(String text, int columns)|초기 문자열이 text이고 길이가 columns인 텍스트 필드를 생성한다.|

#### JTextField 클래스의 주요 메서드

|메서드|설명|
|-----|------|
|String getText()|텍스트 필드에 입력된 문자열을 구한다.|
|void setText(String text)|지정된 문자열을 텍스트 필드에 쓴다.|
|void setEditable(boolean)|텍스트를 입력할 수 있는지 없는지 설정한다.|
|boolean isEditable()|텍스트를 입력할 수 있는지 없는지 반환한다.|

### TextArea
- JTextArea 클래스는 여러 줄의 문자열을 입력할 수 있는 컴포넌트이다. 
- JTextArea는 창의 크기보다 많은 문자열을 입력하더라도 자동으로 스크롤바가 생기지 않는다.
- 따라서 스크롤바의 기능을 사용하기 위해서는 JScrollPane 클래스를 사용해서 표시해야 한다. 

#### JTextArea 클래스의 생성자

|생성자|설명|
|-----|------|
|JTextArea()|초기 문자열이 null이고 행과 열이 각각 0인 텍스트에어리어를 생성한다.|
|JTextArea(String text)|초기 문자열이 text이고 행과 열이 각각 0인 텍스트에어리어를 생성한다.|
|JTextArea(int rows, int columns)|초기 문자열이 null이고 행이 rows이고 열이 columns인 텍스트에어리어를 생성한다.|
|JTextArea(String text, int rows, int columns)|초기 문자열이 text이고 행이 rows이고 열이 columns인 텍스트에어리어를 생성한다.|

#### JPasswordFIeld 
- JPasswordField  클래스는 비밀번호와 같이 입력받은 글자를 보여주지 않아야 할 때 사용하는 컴포넌트이다.

|생성자|설명|
|-----|------|
|JPasswordField()|JPasswordFIeld를 생성한다.|
|JPasswordField(String text)|초기 문자열이 text인 패스워드인 필드를 생성한다.|
|JPasswordFIeld(int columns)|열의 수(길이)가 columns인 패스워드 필드를 생성한다.|
|JPasswordField(String text, int columns)|초기 문자열이 text이고, 열의 수가 columns인 패스워드 필드를 생성한다.|

#### day22/gui/JLabelText.java
```
package day22.gui;

import javax.swing.*;
import java.awt.*;

public class JLabelText extends JFrame {
	JTextField tf;
	JTextArea ta;
	JPasswordField pf;
	
	JLabelText() {
		super("JLabelText");
		setLayout(new FlowLayout());
		setSize(300, 200);
		setLocation(300, 300);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		JLabel lb1 = new JLabel("이름");
		JLabel lb2 = new JLabel("주소");
		JLabel lb3 = new JLabel("비밀");
		
		tf = new JTextField(20);
		ta = new JTextArea(7, 20);
		pf = new JPasswordField(20);
		
		add(lb1);
		add(tf);
		add(lb2);
		add(ta);
		add(lb3);
		add(pf);
	}
	
	public static void main(String[] args) {
		JLabelText jlt = new JLabelText();
	}
}
```
- 실행결과

![스윙6](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/1.JAVA(84%EC%8B%9C%EA%B0%84)/22%EC%9D%BC%EC%B0%A8(3h)%20-%20%EB%84%A4%ED%8A%B8%EC%9B%8C%ED%82%B9%2C%20%EC%82%AC%EC%9A%A9%EC%9E%90%20%EC%9D%B8%ED%84%B0%ED%8E%98%EC%9D%B4%EC%8A%A4(%EC%8A%A4%EC%9C%99)/images/%EC%8A%A4%EC%9C%996.png)

### 버튼(Button)
- AbstractButton 클래스는 JButton, JToggleButton, JCheckBox, JRadioButton 클래스의 공통적인 것들을 정의한 추상 클래스이다.
- 이 4개의 클래스인 스윙 버튼들은 AbstractButton 클래스의 서브클래스로 정의되어 있다. 
- 스윙의 버튼들은 아이콘을 버튼화하여 사용할 수 있는 방법 등 AWT에서 제공하지 않는 다양한 기능들을 제공한다.


#### AbstractButton클래스의 주요 메서드

|메서드|설명|
|-----|------|
|void doClick()|버튼을 누른 것처럼 이벤트를 발생시킨다.|
|void getText()|버튼의 글자를 구한다(반환한다).|
|void setBounderPainted(boolean value)|버튼의 경계를 나타내거나 감춘다.|
|void setContentAreaFilled(boolean value)|버튼의 배경을 채울 것인지를 지정한다.|
|void setEnabled(boolean value)|버튼을 활성화나 비활성화 한다.|
|void setVisible(boolean value)|버튼을 버이게 하거나 감추게 한다.|


### JButton
- JButton은 클릭 기능을 제공한다.
- JButton클래스는 문자열 또는 아이콘을 사용하여 버튼을 생성할 수가 있으며, AbstractButton 클래스로부터 상속받는다.|

#### JButton 클래스의 생성자

|생성자|설명|
|-----|------|
|JButton()|text와 아이콘을 사용하지 않는 버튼을 생성한다.|
|JButton(Icon icon)|아이콘을 가진 버튼을 생성한다.|
|JButton(String text)|문자열 기반인 text를 가진 버튼을 생성한다.|
|JButton(String text, Icon icon)|문자열인 text와 아이콘을 가진 버튼을 생성한다.|

#### JButton 클래스의 메서드

|메서드|설명|
|-----|------|
|boolean isDefaultButton()|이 버튼이 RootPane의 기본 버튼인지 알아낸다.|
|boolean isDefaultCapable()|이 버튼이 RootPane의 기본 버튼이 될 수 있는지 알아낸다.|
|void setDefaultCapable(boolean defaultCapable)|이 버튼이 RootPane의 기본 버튼이 될 수 있는지의 여부를 정한다.|


#### day22/gui/JButtonTest.java
```
package day22.gui;

import javax.swing.*;
import java.awt.*;

public class JButtonTest extends JFrame {
	JButton jbtn1, jbtn2, jbtn3;
	JButtonTest() {
		super("버튼(JButton) 추가");
		setLayout(new FlowLayout());
		
		jbtn1 = new JButton("입력");
		add(jbtn1);
		jbtn2 = new JButton("추가");
		add(jbtn2);
		jbtn3 = new JButton("삭제");
		add(jbtn3);
		
		setSize(300, 200);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		new JButtonTest();
	}
}
```

![스윙7](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/1.JAVA(84%EC%8B%9C%EA%B0%84)/22%EC%9D%BC%EC%B0%A8(3h)%20-%20%EB%84%A4%ED%8A%B8%EC%9B%8C%ED%82%B9%2C%20%EC%82%AC%EC%9A%A9%EC%9E%90%20%EC%9D%B8%ED%84%B0%ED%8E%98%EC%9D%B4%EC%8A%A4(%EC%8A%A4%EC%9C%99)/images/%EC%8A%A4%EC%9C%997.png)


### JCheckBoxs
- JCheckBox 클래스는 체크 박스 기능을 제공하며, AbstractButton 클래스로부터 상속받는다. 

#### JCheckBox 클래스의 생성자

|생성자|설명|
|-----|------|
|JCheckBox()|글자와 아이콘을 사용하지 않고, 선택되지 않은 상태의 JCheckBox를 생성한다.|
|JCheckBox(Icon icon)|아이콘을 사용하고, 선택되지 않은 상태의 JCheckBox를 생성한다.|
|JCheckBox(Icon icon, boolean selected)|아이콘을 사용하는 JCheckBox를 생성한다. selected가 true이면 체크박스가 선택된 상태로 나나나며, false이면 선택되지 않은 상태로 나타난다.|
|JCheckBox(String text)|글자를 사용하는 선택되지 않은 상태의 JCheckBox를 생성한다.|
|JCheckBox(String text, boolean selected)|글자와 아이콘을 사용하는 선택되지 않은 상태의 JCheckBox를 생성한다.|
|JCheckBox(String text, Icon icon, boolean selected)|글자와 아이콘을 사용하는 JCheckBox를 생성한다. selected가 true이면 체크박스가 선택된 상태로 나타나며, false이면 선택되지 않은 상태로 나타난다.|

- 체크 박스의 상태는 다음의 메서드를 이용하여 설정할 수가 있으며, selected를 true로 설정하면 체크 박스가 선택된 상태로 나타난다.
```
void setSelected(boolean selected)
```
### JRadioButton
- JRadioButton 클래스는 라디오 버튼 기능을 제공하며, AbstractButton 클래스로부터 상속받는다. 

#### JRadioButton 클래스의 생성자

|생성자|설명|
|-----|------|
|JRadioButton()|글자가 없고 선택되지 않은 상태의 JRadioButton을 생성한다.|
|JRadioButton(Icon icon)|아이콘을 사용하고 선택되지 않은 상태의 JRadioButton을 생성한다.|
|JRadioButton(Icon icon, boolean selected)|아이콘을 사용하는 JRadioButton을 생성한다. selected가 true이면 체크박스가 선택된 상태로 나타나며, false이면 선택되지 않은 상태로 나타난다.|
|JRadioButton(String text)|글자를 사용하는 선택되지 않은 상태의 JRadioButton을 생성한다.|
|JRadioButton(String text, boolean selected)|글자를 사용하는 JRadioButton을 생성한다. selected가 true이면 체크박스가 선택된 상태로 나타나며, false이면 선택되지 않은 상태로 나타난다.|
|JRadioButton(String text, Icon icon)|글자의 아이콘을 사용하는 선택되지 않은 상태의  JRadioButton을 생성한다.|
|JRadioButton(String text, Icon icon, boolean selected)|글자와 아이콘을 사용하는 JRadioButton을 생성한다. selected가 true이면 체크박스가 선택된 상태로 나타나며, false이면 선택되지 않은 상태로 나타난다.|

- 여러개의 라디오 버튼은 ButtonGroup을 사용하여 하나의(논리적) 그룹으로 묶을 수가 있다. 그룹으로 묶으면 여러 개의 라디오 버튼에서 하나만이 선택되어진다. 라디오 버튼을 그룹으로 묶으면 ButtonGroup 클래스에서 제공하는 add()메서드를 사용한다. 

```
void add(AbstractButton ab)
```

#### day22/gui/JCheckBoxButton.java
```
package day22.gui;

import javax.swing.*;
import java.awt.*;

public class JCheckBoxButton extends JFrame {
	JCheckBox jcb1, jcb2, jcb3;
	JRadioButton jrb1, jrb2, jrb3, jrb4, jrb5;
	JPanel jp1, jp2, jp3;
	
	JCheckBoxButton() {
		super("체크박스와 라디오 버튼 만들기");
		
		// 체크 박스 등록 
		jp1 = new JPanel();
		jcb1 = new JCheckBox("음악감상", true);
		jcb2 = new JCheckBox("등산", true);
		jcb3 = new JCheckBox("조깅", false);
		jp1.add(jcb1);
		jp1.add(jcb2);
		jp1.add(jcb3);
		
		add(jp1, "North");
		
		// 결혼여부 라디오 버튼 등록
		jp2 = new JPanel();
		jrb1 = new JRadioButton("결혼", true);
		jrb2 = new JRadioButton("미혼", false);
		ButtonGroup bg1 = new ButtonGroup();
		bg1.add(jrb1);
		bg1.add(jrb2);
		
		jp2.add(jrb1);
		jp2.add(jrb2);
		add(jp2, "Center");
		
		// 주거형 라디오 버튼 등록
		jp3 = new JPanel();
		jrb3 = new JRadioButton("자가", true);
		jrb4 = new JRadioButton("전세", false);
		jrb5 = new JRadioButton("월세", false);
		
		ButtonGroup bg2 = new ButtonGroup();
		bg2.add(jrb3); 
		bg2.add(jrb4);
		bg2.add(jrb5);
		
		jp3.add(jrb3);
		jp3.add(jrb4);
		jp3.add(jrb5);
		add(jp3, "South");
		
		setSize(300, 200);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		new JCheckBoxButton();
	}
}
```
- 실행결과

![스윙8](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/1.JAVA(84%EC%8B%9C%EA%B0%84)/22%EC%9D%BC%EC%B0%A8(3h)%20-%20%EB%84%A4%ED%8A%B8%EC%9B%8C%ED%82%B9%2C%20%EC%82%AC%EC%9A%A9%EC%9E%90%20%EC%9D%B8%ED%84%B0%ED%8E%98%EC%9D%B4%EC%8A%A4(%EC%8A%A4%EC%9C%99)/images/%EC%8A%A4%EC%9C%998.png)

### JComboBox

- JComboBox 클래스는 텍스트 필드와 풀다운 리스트를 조합한 형태의 콤보 박스의 기능을 제공한다. 콩보 박스는 텍스트 필드에 하나의 항목만 나타내지만, 마우스로 항목을 선택하면 풀다운 형태의 리스트를 제공한다.

#### JComboBox 클래스의 생성자

|생성자|설명|
|-----|------|
|JComboBox()|아이템이 없는 콤보박스를 생성한다.|
|JComboBox(Vector items)|특정한 Vector로 부터 아이템을 취하는(콤보 박스를 초기화시키는) 콤보박스를 생성한다.|
|JComboBox(Object[] items)|배열 items로부터 아이템을 취하는 콤보박스를 생성한다.|


#### JComboBox 메서드

|메서드|설명|
|-----|------|
|void setSelectedItem(Object anObject)|anObject가 리스트에 있으면 그 아이템을 선택하고 없으면 리스트의 첫 번째 아이템을 선택한다.|
|Object getSelectedItem()|선택된 아이템을 구한다.|
|void setSelectedIndex(int anIndex)|anIndex에 위치한 아이템을 선택한다.|
|int getSelectedIndex()|선택된 아이템의 인덱스를 구한다. 선택된 아이템이 없거나 사용자가 필드에 리스트에 없는 값을 입력했을 경우 -1을 리턴한다.|
|void addItem(Object anObject)|콤보박스의 아이템 리스트에 항목인 anObject를 추가한다. 이 메서드는 JComboBox가 기본 데이터 모델을 사용할 때에만 작동한다.|
|void InsertItemAt(Object anObject, int index)|주어진 인덱스에 아이템을 삽입한다. 이 메소드는 JComboBox가 기본 데이터 모델을 사용할 때에만 작동한다.|
|void removeItem(Object anObject)|아이템 리스트에서 아이템을 삭제한다. 이 메소드는 JComboBox가 기본 데이터 모델을 사용할 때에만 작동한다.|
|void removeItem(int anIndex)|anIndex에 위치한 아이템을 삭제한다. 이 메모스든 JCheckBox가 기본 데이터 모델을 사용할 때에만 작동한다.|
|void removeAllItems()|모든 아이템을 삭제한다. 이 메서드는 JComboBox가 기본 데이터 모델을 사용할 때에만 작동한다.|


#### day22/gui/JComboBoxTest.java
```
package day22.gui;

import javax.swing.*;
import java.awt.*;

public class JComboBoxTest extends JFrame {
	JCheckBox jcb1, jcb2, jcb3;
	JComboBox<String> jcm1;
	JPanel jp1;
	String[] title = {"C", "비주얼베이직", "JAVA 프로그래밍", "자료구조", "이산수학"};
	JComboBoxTest() {
		super("콤보 박스 만들기");
		setLayout(new FlowLayout());
		jp1 = new JPanel();
		jcb1 = new JCheckBox("컴퓨터공학", true);
		jcb2 = new JCheckBox("전자상거래", true);
		jcb3 = new JCheckBox("경영학", false);
		jp1.add(jcb1);
		jp1.add(jcb2);
		jp1.add(jcb3);
		add(jp1);
		
		jcm1 = new JComboBox<>(title);
		add(jcm1);
		setSize(300, 250);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		new JComboBoxTest();
	}
}
```
- 실행결과

![스윙9](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/1.JAVA(84%EC%8B%9C%EA%B0%84)/22%EC%9D%BC%EC%B0%A8(3h)%20-%20%EB%84%A4%ED%8A%B8%EC%9B%8C%ED%82%B9%2C%20%EC%82%AC%EC%9A%A9%EC%9E%90%20%EC%9D%B8%ED%84%B0%ED%8E%98%EC%9D%B4%EC%8A%A4(%EC%8A%A4%EC%9C%99)/images/%EC%8A%A4%EC%9C%999.png)

### JScollPane
- JScollPane 클래스는 스크롤바의 기능을 제공한다.
- 보편적으로 JScrollPane 클래스는 패널(JPanel) 클래스에 스크롤바를 설정하는데 사용된다.
- 필요에 따라 패널에 수평이나 수직 스크롤바를 설정한다.

#### JScrollPane 클래스의 생성자

|생성자|설명|
|-----|------|
|JScrollPane()|수직, 수평 스크롤바가 필요할 때 표시되는 스크롤판을 생성한다.|
|JScollPane(Component view)|스크롤바가 추가될 컴포넌트 view에 스크롤판을 생성한다.|
|JScrollPane(Component view, int vsb, in hsb)|스크롤바가 추가될 컴포넌트 view에 스크롤판을 생성한다. 수직(vsb), 수평(hsb) 스크롤바를 설정하기 위한 상수가 올수 있다.|
|JScrollPane(int vsb, int hsb)|vsb와 hsb의 값에 따라 수직, 수평 스크롤바를 표시하는 스크롤판을 생성한다.|

#### vsb와 hsb의 상수 값

<table>
<thead>
	<tr>
		<th>구분</th>
		<th>상수</th>
		<th>설명</th>
	</tr>
</thead>
<tbody>
	<tr>
		<td rowspan='3'>vsb(수직)</td>
		<td>VERTICAL_SCROLLBAR_ALWAYS</td>
		<td>항상 수직 스크롤바를 표시한다.</td>
	</tr>
	<tr>
		<td>VERTICAL_SCROLLBAR_AS_NEEDED</td>
		<td>필요한 때만 수직 스크롤바를 표시한다.|
	</tr>
	<tr>
		<td>VERTICAL_SCROLLBAR_NEVER</td>
		<td>수직 스크롤바를 표기하지 않는다.</td>
	</tr>
	<tr>
		<td rowspan='3'>hsb(수평)</td>
		<td>HORIZONTAL_SCROLLBAR_ALWAYS</td>
		<td>항상 수평 스크롤바를 표시한다.</td>
	</tr>
	<tr>
		<td>HORIZONTAL_SCROLLBAR_AS_NEEDED</td>
		<td>필요할 때만 수평 스크롤바를 표시한다.</td>
	</tr>
	<tr>
		<td>HORIZONTAL_SCROLLBAR_NEVER</td>
		<td>수평 스크롤바를 표시하지 않는다.</td>
	</tr>
</tbody>
</table>


#### day22/gui/JScrollPaneTest.java
```
package day22.gui;

import javax.swing.*;
import java.awt.*;

public class JScrollPaneTest extends JFrame {
	JPanel jp;
	
	JScrollPaneTest() {
		super("JScrollPane Test");
		setSize(300, 300);
		setLayout(new BorderLayout());
		setLocation(300, 300);
		setVisible(true);
		
		jp = new JPanel();
		jp.setLayout(new GridLayout(10, 5));
		int cnt = 1;
		for (int i = 1; i <= 10; i++) {
			for (int j = 1; j <= 5; j++) {
				jp.add(new JButton("버튼" + cnt));
				cnt++;
			}
		}
		
		// 수직, 수평 스크롤바를 설정하기 위한 상수를 얻음
		int v = ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS;
		int h = ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS;
		JScrollPane  js = new JScrollPane(jp, v, h);
		add(js, BorderLayout.CENTER);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}
	
	public static void main(String[] args) {
		new JScrollPaneTest();
		
	}
}
```

- 실행결과

![스윙](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/1.JAVA(84%EC%8B%9C%EA%B0%84)/22%EC%9D%BC%EC%B0%A8(3h)%20-%20%EB%84%A4%ED%8A%B8%EC%9B%8C%ED%82%B9%2C%20%EC%82%AC%EC%9A%A9%EC%9E%90%20%EC%9D%B8%ED%84%B0%ED%8E%98%EC%9D%B4%EC%8A%A4(%EC%8A%A4%EC%9C%99)/images/%EC%8A%A4%EC%9C%9910.png)


### JTable
- JTable 클래스는 데이터를 테이블 형태인 행과 열로 나타내고자 할 때 사용한다.
- JTable 클래스로 나타낸 테이블에서 행은 마우스를 이용하여 경계선을 조정하고 위치를 바꿀 수 있다.

#### JTable 클래스와 생성자

|생성자|설명|
|-----|------|
|JTable()|테이블을 생성한다.|
|JTable(int numRows, int numColumns)|행의 수가 numRows이고 열의 수가 numColumns인 테이블을 생성한다.|
|JTable(Object[][] rowData, Object[] columnNames)|테이블에 나타낼 2차원 배열인 rowData와 행의 제목을 나타내는 일차원 배열인 columnNames을 가지고 테이블을 생성한다.|

- 테이블의 각 행에 들어갈 데이터인 이차원 배열 객체를 생성한다.
- 테이블(JTable) 객체 생성한다.
- JScrollPane에 테이블을 붙인다.

#### day22/gui/JTableTest.java
```
package day22.gui;

import javax.swing.*;
import java.awt.*;

public class JTableTest extends JFrame {
	JTableTest() {
		super("JTable Test");
		setSize(300, 300);
		setLocation(300, 300);
		setLayout(new BorderLayout());
		String[] title = {"사번", "성명", "부서"};
		String[][] data = {{"1", "고애신", "총무과"}, {"2", "최유신", "인사과"}, {"3", "구동매", "전산과"}};
		JTable table = new JTable(data, title);
		int v = ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED;
		int h = ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED;
		
		JScrollPane js = new JScrollPane(table, v, h);
		add(js, BorderLayout.CENTER);
		
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		new JTableTest();
	}
}
```
- 실행결과

![스윙11](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/1.JAVA(84%EC%8B%9C%EA%B0%84)/22%EC%9D%BC%EC%B0%A8(3h)%20-%20%EB%84%A4%ED%8A%B8%EC%9B%8C%ED%82%B9%2C%20%EC%82%AC%EC%9A%A9%EC%9E%90%20%EC%9D%B8%ED%84%B0%ED%8E%98%EC%9D%B4%EC%8A%A4(%EC%8A%A4%EC%9C%99)/images/%EC%8A%A4%EC%9C%9911.png)

### 메뉴 - JMenuBar, JMenu, JMenuItem
- 스윙에서 메뉴 관련 클래스는 JMenuBar, JMenu, JMenuItem, JCheckBoxMenuItem, JRadioButtonMenuItem이 있다.
- 메뉴는 setJMenuBar() 메서드를 제공하는 컨테이너의 객체에만 사용할 수 있다. 이러한 컨테이너는 최종 컨테이너로 사용되는 JFrame 컨테이너만 가능하다.

#### 메뉴의 구조

![스윙12](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/1.JAVA(84%EC%8B%9C%EA%B0%84)/22%EC%9D%BC%EC%B0%A8(3h)%20-%20%EB%84%A4%ED%8A%B8%EC%9B%8C%ED%82%B9%2C%20%EC%82%AC%EC%9A%A9%EC%9E%90%20%EC%9D%B8%ED%84%B0%ED%8E%98%EC%9D%B4%EC%8A%A4(%EC%8A%A4%EC%9C%99)/images/%EC%8A%A4%EC%9C%9912.png)


#### JMenuBar 클래스의 생성자

|생성자|설명|
|-----|------|
|JMenuBar()|메뉴바를 생성한다.|

#### JMenu 클래스의 생성자

|생성자|설명|
|-----|------|
|JMenu()|레이블인 문자열이 없는 메뉴를 생성한다.|
|JMenu(String s)|레이블로 문자열 s를 사용하는 메뉴를 생성한다.|
|JMenu(String s, boolean b)|레이블로 문자열 s를 사용하는 메뉴를 생성한다. b의 값이 true인 경우 메뉴를 분리할 수 있다.|


#### JMenuItem 클래스의 생성자

|생성자|설명|
|-----|------|
|JMenuItem()|메뉴의 레이블로 문자열이나 아이콘이 없는 메뉴 아이템을 생성한다.|
|JMenuItem(Icon icon)|메뉴의 레이블로 아이콘을 사용하는 메뉴아이템을 생성한다.|
|JMenuItem(String text)|메뉴의 레이블로 문자열을 사용하는 메뉴아이템을 생성한다.|
|JMenuItem(String text, Icon icon)|메뉴의 레이블로 문자열과 아이콘을 사용하는 메뉴아이템을 생성한다.|
|JMenuItem(String text, int mnemonic)|메뉴의 레이블로 문자열과 키보드 mnemonic(단축키)를 갖는 메뉴아이템을 생성한다.|

#### day22/gui/JMenuTest.java
```
package day22.gui;

import javax.swing.*;
import java.awt.*;

public class JMenuTest extends JFrame {
	JMenuTest() {
		super("JMenuTest");
		setSize(300, 300);
		setLocation(300, 300);
		
		JMenuBar jmb = new JMenuBar();
		JMenu jmu1 = new JMenu("파일");
		JMenu jmu2 = new JMenu("편집");
		JMenu jmu3 = new JMenu("보기");
		
		JMenuItem jmi1 = new JMenuItem("새로만들기");
		JMenuItem jmi2 = new JMenuItem("열기");
		JMenuItem jmi3 = new JMenuItem("저장");
		
		jmu1.add(jmi1);
		jmu1.add(jmi2);
		jmu1.add(jmi3);
		
		JMenuItem jmi4 = new JMenuItem("잘라내기");
		JMenuItem jmi5 = new JMenuItem("복사");
		JMenuItem jmi6 = new JMenuItem("붙여넣기");
		
		jmu2.add(jmi4);
		jmu2.add(jmi5);
		jmu2.add(jmi6);
		
		JMenuItem jmi7 = new JMenuItem("도구모음");
		JMenuItem jmi8 = new JMenuItem("상태표시줄");
		
		jmu3.add(jmi7);
		jmu3.add(jmi8);
		
		jmb.add(jmu1);
		jmb.add(jmu2);
		jmb.add(jmu3);
		
		setJMenuBar(jmb);
		
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		new JMenuTest();
	}
}
```
- 실행결과

![스윙13](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/1.JAVA(84%EC%8B%9C%EA%B0%84)/22%EC%9D%BC%EC%B0%A8(3h)%20-%20%EB%84%A4%ED%8A%B8%EC%9B%8C%ED%82%B9%2C%20%EC%82%AC%EC%9A%A9%EC%9E%90%20%EC%9D%B8%ED%84%B0%ED%8E%98%EC%9D%B4%EC%8A%A4(%EC%8A%A4%EC%9C%99)/images/%EC%8A%A4%EC%9C%9913.png)


### JPopupMenu
- JPopupMenu 클래스는 팝업 메뉴 기능을 제공한다.
- 일반적으로 팝업 메뉴는 마우스의 오른쪽 버튼을 누르거나(mousePressed), 해제(mouseReleased)할 때 수행한다.

|생성자|설명|
|-----|------|
|JPopupMenu()|팝업 메뉴를 생성한다.|
|JPopupMenu(String label)|기술된 텍스트를 팝업 메뉴의 레이블로 사용하는 팝업메뉴를 생성한다.|

#### day22/gui/JPopupMenuTest.java
```
package day22.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class JPopupMenuTest extends JFrame {
	
	
	JPopupMenuTest() {
		super("팝업메뉴에서 항목 선택");
		
		String[] title = {"사번", "성명", "부서" };
		JRadioButtonMenuItem[] rbm = new JRadioButtonMenuItem[3];
		
		JPopupMenu pmenu = new JPopupMenu();
		ButtonGroup tgroup = new ButtonGroup();
		
		for (int i = 0; i < rbm.length; i++) {
			rbm[i] = new JRadioButtonMenuItem(title[i]);
			pmenu.add(rbm[i]);
			tgroup.add(rbm[i]);
		}
		
		// 마우스 이벤트를 리스터에 등록
		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				checkForTriggerEvent(e);
			}
			
			public void mouseReleased(MouseEvent e) {
				checkForTriggerEvent(e);
			}
			
			// 마우스 오른쪽 버튼을 누르거나 해제할 때 발생
			private void checkForTriggerEvent(MouseEvent e) {
				if (e.isPopupTrigger()) 
					pmenu.show(e.getComponent(), e.getX(), e.getY());
			}
		});
		
		setSize(300, 200);
		setVisible(true);
	}
	public static void main(String[] args) {
		new JPopupMenuTest();
	}
}
```
- 실행결과

![스윙14](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/1.JAVA(84%EC%8B%9C%EA%B0%84)/22%EC%9D%BC%EC%B0%A8(3h)%20-%20%EB%84%A4%ED%8A%B8%EC%9B%8C%ED%82%B9%2C%20%EC%82%AC%EC%9A%A9%EC%9E%90%20%EC%9D%B8%ED%84%B0%ED%8E%98%EC%9D%B4%EC%8A%A4(%EC%8A%A4%EC%9C%99)/images/%EC%8A%A4%EC%9C%9914.png)

### JTabbedPane
- JTabbedPane 클래스는 탭(Tab)의 기능을 제공한다.

|생성자|설명|
|-----|------|
|JTabbedPane()|비어있는 탭팬을 생성한다. 기본적인 탭의 위치는  JTabbedPane.TOP이 기본값이 된다.|
|JTabbedPane(int tabPlacement)|비어 있는 J탭팬을 생성한다. tabPlacement의 값에 따라 탭의 위치가 정해진다. tabPlacement의 값에는 JTabbedPane.TOP, JTabbedPane.BOTTOM, JTabbedPane.LEFT, JTabbedPane.RIGHT가 있다.|

- 탭(JTabbedPane)객체를 생성한다.
```
JTabbedPane jtp = new JTabbedPane(JTabbedPane.TOP);
```

- 탭에 addTab("탭의 레이블 이름", 추가할 객체 이름) 메소드를 사용하여 필요한 개수의 컴포넌트를 추가한다.

```
// 추가할 패널 객체 생성(만약 패널을 추가할 경우)
JPanel jpn1 = new JPanel();
JPanel jpn2 = new JPanel();

jtp.addTab("기본내용", jpn1);
jtp.addTab("기본내용", jpn2);
```


#### day22/gui/JTabbedPaneFrame.java
```
package day22.gui;

import javax.swing.*;
import java.awt.*;

public class JTabbedPaneFrame extends JFrame {
	public JTabbedPaneFrame() {
		super("사원 개인정보 조회(JTabbedPane)");
		
		JTabbedPane jtp = new JTabbedPane(JTabbedPane.TOP);
		JPanel jpn1 = new JPanel();
		JPanel jpn2 = new JPanel();
		JPanel jpn3 = new JPanel();
		bTable jt1 = new bTable();
		eTable jt2 = new eTable();
		fTable jt3 = new fTable();
		
		jpn1.add(jt1);
		jpn2.add(jt2);
		jpn3.add(jt3);
		
		jtp.addTab("기본내용", jpn1);
		jtp.addTab("추가내용", jpn2);
		jtp.addTab("보안내용", jpn3);
		
		add(jtp, BorderLayout.CENTER);
		
		setSize(500, 200);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		JTabbedPaneFrame jt = new JTabbedPaneFrame();
		jt.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}


class bTable extends JPanel {
	public bTable() {
		String[] title = {"사번", "성명", "부서"};
		String[][] data = {{"1", "이름1", "총무과"}, {"2", "이승엽", "인사과"}, {"3", "박태환", "전산과"}};
		JTable table = new JTable(data, title);
		int v = ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS;
		int h = ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS;
		JScrollPane js = new JScrollPane(table, v, h);
		add(js);
	}
}

class eTable extends JPanel {
	public eTable() {
		String[] title = {"입사일", "주소", "전화" };
		String[][] data = {{"2001-1-1", "은평구 응암동", "303-5555"}, {"2000-5-30", "마토구 도화동", "5555-6666"}, {"2008-1-1", "구로구 신림동", "777-1234"}};
		JTable table = new JTable(data, title);
		int v = ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS;
		int h = ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS;
		JScrollPane js = new JScrollPane(table, v, h);
		add(js);
	}
}

class fTable extends JPanel {
	public fTable() {
		String[] title = {"호봉", "근무평점"};
		String[][] data = {{"0506", "보통"}, {"0401", "우수"}, {"0701", "미흡"}};
		JTable table = new JTable(data, title);
		int v = ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS;
		int h = ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS;
		JScrollPane js = new JScrollPane(table, v, h);
		add(js);
	}
}
```
- 실행결과

![스윙15](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/1.JAVA(84%EC%8B%9C%EA%B0%84)/22%EC%9D%BC%EC%B0%A8(3h)%20-%20%EB%84%A4%ED%8A%B8%EC%9B%8C%ED%82%B9%2C%20%EC%82%AC%EC%9A%A9%EC%9E%90%20%EC%9D%B8%ED%84%B0%ED%8E%98%EC%9D%B4%EC%8A%A4(%EC%8A%A4%EC%9C%99)/images/%EC%8A%A4%EC%9C%9915.png)


## 스윙 레이아웃
- 레이아웃 매니저(배치 관리자)는 AWT에서 제공하는 레이아웃 외에 스윙에서 5가지의 레이아웃을 추가적으로 제공하고 있다.
- AWT 레이아웃은 BorderLayout, FlowLayout, GridLayout, GridBagLayout, CardLayout이 있다.
- 스윙 레이아웃에는 ScrollPaneLayout, ViewPortLayout, BoxLayout, OverlayLayout, SpringLayout이 있다. 
- 스윙의 레이아웃 중에서 ScrollPaneLayout, ViewportLayout은 실제로 사용하지 않고 컴포넌트 내부에서 사용하며, 나머지 3개의 레이아웃인 BoxLayout, OverLayLayout, SpringLayout은 AWT에서 제공하는 레이아웃과 같은 방법으로 사용한다.

### 레이아웃 매니저의 사용법 
- 필요한 배치를 지원하는 레이아웃 매니저 클래스 객체를 생성한다.
```
FlowLayout flayout = new FlowLayout(); // 플로우 레이아웃인 경우 
BorderLayout blayout = new BorderLayout(); // 보더 레이아웃인 경우
```

- 생성된 레이아웃 매니저 클래스 객체를 setLayout() 메서드를 이용하여 컨테이너에 설정한다.
```
setLayout(flayout);
```

- add() 메서드를 이용하여 추가하는 컴포넌트는 설정된 레이아웃 매니저에 의하여 자동으로 배치가 일어난다.
```
add(new JButton("첫번째"));
```


### FlowLayout
- 플로우 레이아웃은 컨테이너에 컴포넌틀를 가운데 정렬하며 왼쪽에서 오른쪽으로 한 줄로 늘어놓는 배치 기능을 한다. 
- 컴포넌트를 한 줄에 배치하지 못하고 남으면 자연스럽게 다름줄에 추가하여 배치된다.


#### day22/gui/FlowLayoutTest.java
```
package day22.gui;

import java.awt.*;
import javax.swing.*;

public class FlowLayoutTest extends JFrame {
	public FlowLayoutTest() {
		super("FlowLayout");
		
		// FlowLayout 매니저 생성
		FlowLayout flayout = new FlowLayout();
		
		// 컨테이너에 설정 
		setLayout(flayout);
		
		add(new JButton("첫번째"));
		add(new JButton("두번째"));
		
		setSize(300, 200);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		FlowLayoutTest flt = new FlowLayoutTest();
		flt.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}
```

- 실행결과

![스윙16](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/1.JAVA(84%EC%8B%9C%EA%B0%84)/22%EC%9D%BC%EC%B0%A8(3h)%20-%20%EB%84%A4%ED%8A%B8%EC%9B%8C%ED%82%B9%2C%20%EC%82%AC%EC%9A%A9%EC%9E%90%20%EC%9D%B8%ED%84%B0%ED%8E%98%EC%9D%B4%EC%8A%A4(%EC%8A%A4%EC%9C%99)/images/%EC%8A%A4%EC%9C%9916.png)

### BorderLayout
- 보더 레이아웃은 컴포넌트에 부착시킬 위치가 이미 정해져있는 레이아웃이다
- 보더(Border)란 경계란 뜻을 가지고 있듯이 다음의 그림과 같이 모두 5개의 영역으로 구분된다. 
- 각 배치에는 East, West, South, North, Center가 있으며, 각 이름은 컴포넌트를 컨테이너에 포함시킬 때 같이 주어진다.

![스윙17](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/1.JAVA(84%EC%8B%9C%EA%B0%84)/22%EC%9D%BC%EC%B0%A8(3h)%20-%20%EB%84%A4%ED%8A%B8%EC%9B%8C%ED%82%B9%2C%20%EC%82%AC%EC%9A%A9%EC%9E%90%20%EC%9D%B8%ED%84%B0%ED%8E%98%EC%9D%B4%EC%8A%A4(%EC%8A%A4%EC%9C%99)/images/%EC%8A%A4%EC%9C%9917.png)


#### day22/gui/BorderLayoutTest.java
```
package day22.gui;

import java.awt.*;
import javax.swing.*;

public class BorderLayoutTest extends JFrame {
	public BorderLayoutTest() {
		super("Borderlayout");
		
		add("North", new Button("North"));
		add(BorderLayout.EAST, new Button("EAST"));
		add(new Button("Center"), BorderLayout.CENTER);
		add(new Button("South"), BorderLayout.SOUTH);
		add(new Button("West"), BorderLayout.WEST);
		
		setSize(300, 200);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		BorderLayoutTest blt = new BorderLayoutTest();
		blt.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}
```

- 실행결과

![스윙18](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/1.JAVA(84%EC%8B%9C%EA%B0%84)/22%EC%9D%BC%EC%B0%A8(3h)%20-%20%EB%84%A4%ED%8A%B8%EC%9B%8C%ED%82%B9%2C%20%EC%82%AC%EC%9A%A9%EC%9E%90%20%EC%9D%B8%ED%84%B0%ED%8E%98%EC%9D%B4%EC%8A%A4(%EC%8A%A4%EC%9C%99)/images/%EC%8A%A4%EC%9C%9918.png)


### GridLayout
- 행렬의 형태를 가지는 레이아웃
- 그리드 레이아웃은 생성할 때 행(컬럼 수)과 열(라인 수)을 지정한다.

```
GridLayout glayout = new GridLayout(3, 5);
setLayout(glayout);
```

#### day22/gui/GridLayoutTest.java
```
package day22.gui;

import java.awt.*;
import javax.swing.*;

public class GridLayoutTest extends JFrame {
	String[] title = {"Num", "/", "*", "-", "7", "8", "9", "+", "4", "5", "6", "=", "1", "2", "3", "Enter", "0", ".", ",", "?"};
	
	public GridLayoutTest() {
		super("그리드 레이아웃");
		
		// 그리드 레이아웃 매니저 생성
		GridLayout glayout = new GridLayout(4, 5);
		
		// 컨테이너에 설정
		setLayout(glayout);
		
		// 각 컴포넌트를 컨테이너에 추가
		JButton btn[] = new JButton[20];
		for(int i = 0; i < 20; i++) {
			btn[i] = new JButton(title[i]);
			add(btn[i]);
		}
		
		setSize(400, 200);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		GridLayoutTest glt = new GridLayoutTest();
		glt.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}
```

- 실행결과

![스윙19](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/1.JAVA(84%EC%8B%9C%EA%B0%84)/22%EC%9D%BC%EC%B0%A8(3h)%20-%20%EB%84%A4%ED%8A%B8%EC%9B%8C%ED%82%B9%2C%20%EC%82%AC%EC%9A%A9%EC%9E%90%20%EC%9D%B8%ED%84%B0%ED%8E%98%EC%9D%B4%EC%8A%A4(%EC%8A%A4%EC%9C%99)/images/%EC%8A%A4%EC%9C%9919.png)

