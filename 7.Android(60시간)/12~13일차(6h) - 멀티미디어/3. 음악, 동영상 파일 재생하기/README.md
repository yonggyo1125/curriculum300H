# 음악 파일 재생하기

- 멀티미디어를 위해 제공하는 미디어 API는 android.media 패키지에 들어 있습니다. 그 안의 여러 클래스들 중에서 핵심이 되는 것은 '미디어플레이어(MediaPlayer) 클래스'입니다. 미디어플레이어 클래스는 음악 파일과 같은 오디오의 재생은 물론 동영상 재생까지 담당합니다. 출시되는 단말에 따라 지원하는 음성/영상 코덱이 다르므로 재생할 수 있는 파일의 종류가 다를 수 있습니다. 하지만 가장 기본적으로 제공되는 코덱만으로도 오디오와 동영상을 재생할 수 있습니다. 오디오 파일을 재생하려면 대상을 지정해야 하는데 이때 사용되는 데이터 소스 지정 방법은 크게 세 가지로 나눌 수 있습니다.

|구분|설명|
|----|------|
|인터넷에 있는 파일 위치 지정|미디어가 있는 위치를 URL로 지정합니다.|
|프로젝트 파일에 포함한 후 위치 지정|앱을 개발하여 배포하는 과정에서 프로젝트의 리소스 또는 애셋(assets) 폴더에 넣은 후 위치를 지정합니다.|
|단말 SD 카드에 넣은 후 위치 지정|단말에 넣어 둔 SD 카드에 파일을 넣은 후 그 위치를 지정합니다.|

- 미디어플레이어로 음악 파일을 재생하는 과정은 다음 그림처럼 세 단계로 나눌 수 있습니다. 첫 번째 단계는 대상 파일을 알려주는 것으로써 setDataSource 메서드로 URL을 지정합니다. 두 번째 단계는 prepare 메서드를 호출하여 재생을 준비합니다. 이 단계에서 미디어플레이어는 대상 파일의 몇 프레임을 미리 읽어 들이고 정보를 확인합니다. 세 번째 단계에서는 start 메서드를 호출하여 음악 파일을 재생합니다.

![image1](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/12~13%EC%9D%BC%EC%B0%A8(6h)%20-%20%EB%A9%80%ED%8B%B0%EB%AF%B8%EB%94%94%EC%96%B4/3.%20%EC%9D%8C%EC%95%85%2C%20%EB%8F%99%EC%98%81%EC%83%81%20%ED%8C%8C%EC%9D%BC%20%EC%9E%AC%EC%83%9D%ED%95%98%EA%B8%B0/images/image1.png)

- ﻿이제 MP3 파일을 재생하거나 중지할 수 있는 간단한 앱을 만들어 보겠습니다 SampleAudioPlayer라는 이름의 프로젝트를 만들고 패키지 이름은 org.koreait.audio.player로 입력합니다. 새로운 프로젝트 창이 열리면 activity_main.xml 파일을 열고 다음과 같이 네 개의 버튼을 레이아웃에 추가하겠습니다.


- 최상위 레이아웃을 LinearLayout으로 바꾸고 orientation 속성 값을 vertical로 설정한 후 네 개의 버튼을 추가합니다. 각 버튼의 text 속성은 '재생', '중지', '일시정지', '재시작'으로 변경합니다. 레이아웃에 추가한 네 개의 버튼 중에서 첫 번째 버튼을 누르면 앞에서 설명했던 것처럼 오디오 재생을 위한 3단계를 거치면서 음악 파일을 재생합니다. 두 번째 버튼은 중지, 세 번째 버튼은 일시정지 그리고 네 번째 버튼은 재시작 기능을 수행하도록 코드를 수정하겠습니다. 

#### active_main.xml 

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    tools:context=".MainActivity">

    <Button
        android:id="@+id/button"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="재생" />

    <Button
        android:id="@+id/button2"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="중지" />

    <Button
        android:id="@+id/button3"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="일시정지" />

    <Button
        android:id="@+id/button4"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="재시작" />
</LinearLayout>
```

![image2](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/12~13%EC%9D%BC%EC%B0%A8(6h)%20-%20%EB%A9%80%ED%8B%B0%EB%AF%B8%EB%94%94%EC%96%B4/3.%20%EC%9D%8C%EC%95%85%2C%20%EB%8F%99%EC%98%81%EC%83%81%20%ED%8C%8C%EC%9D%BC%20%EC%9E%AC%EC%83%9D%ED%95%98%EA%B8%B0/images/image2.png)

- 이제 MainActivity.java 파일을 열고 각 버튼을 눌렀을 때 동작할 코드를 추가합니다.

#### SampleAudioPlayer>/app/java/org.koreait.audio.player/MainActivity.java

```java
package org.koreait.audio.player;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public static final String AUDIO_URL = "https://sites.google.com/site/ubiaccessmobile/sample_audio.mp3";

    MediaPlayer mediaPlayer;
    int position = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playAudio(AUDIO_URL);
                Toast.makeText(getApplicationContext(), "음악 파일 재생 시작됨.", Toast.LENGTH_LONG).show();
            }
        });

        Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mediaPlayer != null) {
                    mediaPlayer.stop();
                    Toast.makeText(getApplicationContext(), "음악 파일 재생 중지됨.", Toast.LENGTH_LONG).show();
                }
            }
        });

        Button button3 = findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mediaPlayer != null) {
                    position = mediaPlayer.getCurrentPosition();
                    mediaPlayer.pause();
                    Toast.makeText(getApplicationContext(), "음악 파일 재생 일시정지됨.", Toast.LENGTH_LONG).show();
                }
            }
        });

        Button button4 = findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mediaPlayer != null && !mediaPlayer.isPlaying()) {
                    mediaPlayer.start();
                    mediaPlayer.seekTo(position);
                    Toast.makeText(getApplicationContext(), "음악 파일 재생 재시작됨.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void playAudio(String url) {
        killMediaPlayer();
        try {
            // MediaPlayer 객체 만들어 시작하기
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setDataSource(url);
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void onDestroy() {
        super.onDestroy();
        killMediaPlayer();
    }

    private void killMediaPlayer() {
        if (mediaPlayer != null) {
            try {
                mediaPlayer.release(); // MediaPlayer 객체의 리소스 해제하기
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
```

- MediaPlayer 객체를 이용해 음악을 재생하는 playAudio 메서드의 구조를 보면 killMediaPlayer 메서드를 호출한 후 차례로 setDataSource, prepare, start 메서드를 호출하고 있습니다. killMediaPlayer메서드는 미디어플레이어 객체가 이미 리소스를 사용하고 있을 경우에 release 메서드를 호출하여 리소스를 해제하는 역할을 합니다. 이렇게 하는 이유는 미디어플레이어를 앱 내에서 재사용하려면 기존에 사용하던 리소스를 먼저 해제해야 하기 때문입니다.

- 재생을 중지하고 다시 시작하기 위해서는 중지한 지점의 위치를 알아야 하므로 [일시정지] 버튼을 눌렀을 때는 getCurrentPosition 메서드를 이용해 현 지점의 위치를 알아 오고 [재시작] 버튼을 눌렀을때는 seekTo 메서드로 중지했을 때의 지점에서부터 재생하도록 만듭니다.

- 이 앱은 인터넷의 다른 서버에 미리 올려둔 음악 파일을 사용하고 있습니다. 그리고 인터넷에서 파일을 받아 오기 때문에 INTERNET 권한이 필요합니다. AndroidManifest.xml 파일을 열고 다음과 같이 INTERNET 권한을 추가합니다. 그리고 \<application\> 태그에 속성을 하나 더 추가합니다.

#### SampleAudioPlayer>/app/manifests/AndroidManifest.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    package="org.koreait.audio.player">

    <uses-permission android:name="android.permission.INTERNET" />

    <application
        android:usesCleartextTraffic="true"
		
		... 생략 
	>
	
	... 생략
	
    </application>

</manifest>
```

- 다음은 앱을 실행한 화면입니다.

![image3](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/12~13%EC%9D%BC%EC%B0%A8(6h)%20-%20%EB%A9%80%ED%8B%B0%EB%AF%B8%EB%94%94%EC%96%B4/3.%20%EC%9D%8C%EC%95%85%2C%20%EB%8F%99%EC%98%81%EC%83%81%20%ED%8C%8C%EC%9D%BC%20%EC%9E%AC%EC%83%9D%ED%95%98%EA%B8%B0/images/image3.png)

- 에뮬레이터에서는 오디오 디바이스가 정상적으로 동작하지 않을 수 있으므로 실제 단말에서 테스트해보는 것이 좋습니다. 음악 파일의 재생이 끝나면 killMediaPlayer 메서드를 호출하여 리소스를 해제하는 것이 필요합니다. 만약 미디어플레이어의 stop 메서드로 재생을 중지했을 때 또 다른 작업을 수행하고 싶다면 MediaPlayer.OnCompletionListener를 구현한 후 미디어플레이어 객체에 등록하면 됩니다.

- 재생이 중지되었을 때 호출되는 메서드는 다음과 같습니다.

```java
public abstract void onCompletion (MediaPlayer mp)
```

* * * 
# 동영상 재생하기

- 동영상 재생 기능은 어려울 것 같아 보이지만 앞서 실습했던 오디오 파일 재생만큼이나 간단히 만들 수 있습니다. 동영상을 재생하고 싶다면 비디오뷰(VideoView) 위젯을 사용하면 되는데 XML 레이아웃에 \<VideoView\> 태그를 추가하기만 하면 동영상 플레이어를 바로 만들 수 있습니다.

> 동영상은 화면에 디스플레이되는 영역이 있으므로 위젯으로 XML 레이아웃에 추가할 수 있습니다. 이 때문에 미디어플레이어 클래스를 사용하는 것보다 더 쉽게 동영상을 재생할 수 있습니다. 말 그대로 XML 레이아웃에 태그를 추가하고 한두줄의 코드만 구현하면 동영상을 재생할 수 있습니다.

- 동영상 재생 기능을 만들기 위해 새로운 SampleVideoPlayer 프로젝트를 만들고 패키지 이름은 org.koreait.video.player로 입력합니다. 프로젝트 창이 열리면 activity_main.xml 파일을 열고 LinearLayout(vertical)로 변경합니다. 그리고 '재생하기'라는 글자가 표시된 한 개의 버튼과 VideoView 위젯을 추가합니다.

#### activity_main.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    tools:context=".MainActivity">

    <Button
        android:id="@+id/button"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="재생하기" />

    <VideoView
        android:id="@+id/videoView"
        android:layout_width="match_parent"
        android:layout_height="wrap_content" />
    
</LinearLayout>
```

- 레이아웃에 추가한 비디오뷰 객체를 자바 코드에서 참조한 후 동영상 파일의 위치를 setVideoURI 메서드로 지정하기만 하면 동영상을 재생할 수 있습니다. 동영상의 재생 상태를 보거나 동영상을 제어할 때 사용되는 미디어컨트롤러(MediaController) 객체는 setMediaController 메서드로 설정할 수 있는﻿데 손가락으로 터치하면 컨트롤러 부분을 보여주게 됩니다. 이 컨트롤러로 비디오 재생 상태를 확인할 수 있습니다. 비디오뷰 객체에는 getDuration이나 pause와 같이 동영상을 제어하는 데 필요한 다른 메서드들도 정의되어 있습니다. 이때 MediaController 클래스는 android.widget을 선택하여 추가하세요.

- 이제 MainActivity.java 파일을 열고 버튼을 클릭했을 때 동작할 코드를 입력합니다.

#### SampleVideoPlayer>/app/java/org.koreait.video.player/MainActivity.java

```java
package org.koreait.video.player;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {
    private static final String VIDEO_URL = "https://sites.google.com/site/ubiaccessmobile/sample_video.mp4";
    VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        videoView = findViewById(R.id.videoView);

        MediaController mc = new MediaController(this);
        videoView.setMediaController(mc); // VideoView에 MediaController 설정하기

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // VideoView에 재생할 대상 설정하고 재생 시작하기
                videoView.setVideoURI(Uri.parse(VIDEO_URL));
                videoView.requestFocus();
                videoView.start();
            }
        });
    }
}
```

- 이 앱도 인터넷에서 파일을 받아 오기 때문에 INTERNET 권한이 필요합니다. AndroidManifest.xml파일을 열고 다음과 같이 INTERNET 권한을 추가합니다. 그리고 \<application\> 태그에 속성을 하나더 추가합니다.

#### SampleVideoPlayer>/app/manifests/AndroidManifest.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    package="org.koreait.video.player">

    <uses-permission android:name="android.permission.INTERNET" />
    <application
        android:usesCleartextTraffic="true"
		... 생략 
	>
	
	... 생략 
    </application>

</manifest>
```

- 앱을 실행하고 [재생하기] 버튼을 누르면 동영상이 재생됩니다. 이 영상에는 소리가 포함되어 있지 않습니다. 

![image4](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/12~13%EC%9D%BC%EC%B0%A8(6h)%20-%20%EB%A9%80%ED%8B%B0%EB%AF%B8%EB%94%94%EC%96%B4/3.%20%EC%9D%8C%EC%95%85%2C%20%EB%8F%99%EC%98%81%EC%83%81%20%ED%8C%8C%EC%9D%BC%20%EC%9E%AC%EC%83%9D%ED%95%98%EA%B8%B0/images/image4.png)

- 화면 가운데에 있는 비디오뷰를 손가락으로 터치하면 아래쪽에 미디어컨트롤러가 나타납니다. 이 컨트롤러의 버튼을 이용하면 동영상 재생을 중지하거나 재시작할 수 있습니다. 미디어컨트롤러는 현재 재생 위치와 함께 동영상 스트림이 얼마나 로딩되었는지를 확인할 수 있도록 프로그레스바(ProgressBar)를 보여줍니다.

> 동영상 재생을 위해서는 VideoView를 사용하거나 또는 MediaPlayer를 사용할 수 있습니다. 만약 동영상을 좀 더 세밀하게 제어하고 싶다면 MediaPlayer를 사용할 수 있습니다. 이때는 좀 더 많은 코드와 세심한 제어가 필요합니다.





