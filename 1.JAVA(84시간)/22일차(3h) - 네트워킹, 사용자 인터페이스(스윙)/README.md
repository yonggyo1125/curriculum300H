# 네트워킹

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


#### 스윙 컨테이너의 상속 관계


## 스윙 프로그램
- 애플리케이션 프로그래밍 컨테이너로 **JFrame**을 사용합니다.
- **JPanel**은 중간 컨테이너로 사용되고 **JFrame**은 최종 컨테이너로 사용됩니다.

### 프레임 : JFrame

## 스윙 컴포넌트



