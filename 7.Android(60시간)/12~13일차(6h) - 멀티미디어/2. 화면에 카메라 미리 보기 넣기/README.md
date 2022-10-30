# 화면에 카메라 미리보기 넣기

- 인텐트와 startActivityForResult 메서드로 사진을 찍는 방법은 그리 복잡하지 않습니다. 또한 단말의 카메라 앱을 사용하기 때문에 단말의 카메라 앱이 기본적으로 제공하는 기능을 그대로 사용할 수 있습니다. 하지만 단순히 사진을 찍기만 하는 것이 아니라 카메라 미리보기 화면에 증강현실을 표현할 아이콘이나 그래픽 등을 보여주고 싶다면 어떻게 해야 할까요? 그리고 똑같은 사진 찍기 기능만을 사용한다고 하더라도 여러분이 만든 앱에서 직접 사진을 찍을 수는 없을까요? 여러분의 앱에 카메라 미리보기와 ﻿사진 찍기 기능을 넣을 수 있지만 코드는 좀 더 많아지게 됩니다. 그리고 서피스뷰(SurfaceView)라는 것으로 카메라 미리보기 화면이 구현되기 때문에 서피스뷰가 무엇인지도 이해해야 합니다.

- 다음 그림을 보면 카메라 미리보기에 사용되는 서피스뷰의 사용 방법을 이해할 수 있습니다.

![image1](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/12~13%EC%9D%BC%EC%B0%A8(6h)%20-%20%EB%A9%80%ED%8B%B0%EB%AF%B8%EB%94%94%EC%96%B4/2.%20%ED%99%94%EB%A9%B4%EC%97%90%20%EC%B9%B4%EB%A9%94%EB%9D%BC%20%EB%AF%B8%EB%A6%AC%20%EB%B3%B4%EA%B8%B0%20%EB%84%A3%EA%B8%B0/images/image1.png)

- 카메라 미리보기 기능을 구현하려면 일반 뷰(View)가 아니라 서피스뷰를 사용해야 합니다. 그런데 서피스뷰는 서피스홀더(SurfaceHolder) 객체에 의해 생성되고 제어되기 때문에 서피스뷰와 서피스홀더 간의 관계를 이해해야 합니다. 서피스뷰는 서피스홀더에 의해 제어된다고 생각하면 쉽습니다. 만약 카메라 객체를 만든 후에 미리보기 화면을 서피스뷰에 보여주고 싶다면 서피스홀더 객체의 setPreviewDisplay 메서드로 미리보기를 설정해 주어야 합니다.

```java
public void setPreviewDisplay(Surface sv)
```

- 필요한 초기화 작업이 끝나면 카메라 객체의 startPreview 메서드를 호출할 수 있으며, 이때부터 카메라로 입력된 영상을 서피스뷰로 화면에 보여주게 됩니다.

- 카메라 미리보기를 한 후 화면의 버튼을 눌러 사진을 찍고 그 사진을 미디어 앨범에 저장하는 가장 간단한 앱을 만들어 보겠습니다. SampleCapture라는 새로운 프로젝트를 만들고 패키지 이름은 org.koreait.capture로 입력합니다. 프로젝트 창이 열리면 activity_main.xml 파일을 열고 최상위 레이아웃을 LinearLayout으로 변경합니다. orientation 속성 값은 vertical로 설정하고 화면 상단에는 '사진 찍기'라는 글자가 표시된 버튼을 배치합니다. 그 아래에는 FrameLayout이 나머지 공간을 꽉 채우도록 합니다. 이 FrameLayout의 id 값은 previewFrame으로 설정되었으며 이 레이아웃 안에 카메라 미리보기 화면이 보이도록 할 것입니다.

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
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_gravity="center_horizontal"
        android:text="사진찍기" />

    <FrameLayout
        android:id="@+id/previewFrame"
        android:layout_width="match_parent"
        android:layout_height="match_parent">

    </FrameLayout>
</LinearLayout>
```

- 카메라 미리보기 화면을 FrameLayout 안에 추가하기 위해 소스 코드에서 CameraSurfaceView라는 이름의 새로운 클래스를 MainActivity 클래스 안에 내부 클래스로 정의하고 그 클래스의 인스턴스 객체를 만들어 추가할 것입니다. 먼저 MainActivity.java 파일을 열고 CameraSurfaceView 객체를 FrameLayout에 추가하고 사진을 찍는 코드를 입력합니다. 여기에서 CameraSufaceView 클래스는 미리 정의되어 있다고 가정하고 입력하기 때문에 빨간색 밑줄이 생겨도 그대로 진행합니다.



```java
package org.koreait.capture;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity {
    CameraSurfaceView cameraView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FrameLayout previewFrame = findViewById(R.id.previewFrame);
        cameraView = new CameraSurfaceView(this);
		previewFrame.addView(cameraView);
		
        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takePicture();
            }
        });
    }

    public void takePicture() {
        // CameraSurfaceView의 capture 메서드 호출하기
        cameraView.capture(new Camera.PictureCallback() {
            @Override
            public void onPictureTaken(byte[] data, Camera camera) {
                try {
                    // 전달받은 바이트 배열을 Bitmap 객체로 만들기
                    Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
                    String outUriStr = MediaStore.Images.Media.insertImage(getContentResolver(), bitmap, "Captured Image", "Captured Image using Camera.");

                    if (outUriStr == null) {
                        Log.d("SampleCapture", "Image insert failed.");
                        return;
                    } else {
                        Uri outUri = Uri.parse(outUriStr);
                        sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, outUri));
                    }

                    camera.startPreview();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
```

- 카메라 미리보기를 구현하는 CameraSurfaceView는 SurfaceView를 상속받아 새로 정의한 후 XML레이아웃에 정의된 FrameLayout에 추가합니다. 상단의 [사진 찍기] 버튼을 누르면 사진을 한 장 찍어 미디어 앨범에 추가하게 되는데 사진을 찍은 결과를 처리하는 코드는 PictureCallback 인터페이스를 구현하는 부분에 들어 있습니다. 이 인터페이스는 CameraSurfaceView에 정의된 capture 메서드를호흡할 때 전달됩니다.

```java
public abstract void onPicture Taken(byte[] data, Camera camera)
```

- 즉, 사진을 찍을 때 자동으로 호출되는 onPictureTaken 메서드로 캡처한 이미지 데이터가 전달됩니다. 그런 다음 이 데이터를 비트맵으로 만들고 MediaStore.Images.Media에 정의된 insertImage 메서드를 이용하여 미디어 앨범에 추가합니다. 이미지 데이터를 비트맵으로 만들기 위해서는 BitmapFactory클래스에 정의된 decodeByteArray 메서드를 이용합니다. insertImage 메서드는 간단한 방법으로 이미지를 추가할 수 있도록 정의된 메서드입니다.

```java
public static final String insertImage (ContentResolver cr, Bitmap source, String title, String description)
```

- 첫 번째 파라미터는 ContentResolver 객체, 두 번째는 메모리에 만들어진 비트맵 객체, 그리고 세 번째와 네 번째 파라미터로는 그 비트맵 이미지의 제목과 내용이 들어갑니다.

- 다음은 서피스뷰를 확장하여 정의한 CameraSurfaceView 클래스입니다. 이 클래스는 SurfaceHolder에 정의된 Callback 인터페이스를 구현하고 있으므로 서피스뷰의 상태가 변경될 때 자동 호출되는 세가지 메서드(surfaceCreated, surfaceChanged, surfaceDestroyed)가 구현되어 있는 것을 볼 수 있습니다. 이 클래스를 MainActivity.java 파일 안에 내부 클래스로 정의합니다.

#### SampleCapture>/app/java/org.koreait.capture/MainActivity.java

```java

... 생략

public class MainActivity extends AppCompatActivity {
		
	... 생략 
		
    // SurfaceView 클래스를 상속하고 Callback 인터페이스를 구현하는 새로운 CameraSurfaceView 클래스 정의
    class CameraSurfaceView extends SurfaceView implements SurfaceHolder.Callback {
        private SurfaceHolder mHolder;
        private Camera camera = null;

        public CameraSurfaceView(Context context) {
            super(context);

            // 생성자에서 서피스 홀더 객체 잠조 후 설정
            mHolder = getHolder();
            mHolder.addCallback(this);
        }

        // 서피스뷰가 만들어질 떄 카메러 객체를 참조한 후 미리보기 화면으로 홀더 객체 생성
        public void surfaceCreated(SurfaceHolder holder) {
            camera = Camera.open();

            try {
                camera.setPreviewDisplay(mHolder);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        // 서피스뷰 화면 크기가 바뀌는 등 변경 시점에 미리보기 시작
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            camera.startPreview();;
        }
        
        // 서피스뷰가 없어질 떄 미리보기 중지
        public void surfaceDestroyed(SurfaceHolder holder) {
            camera.stopPreview();
            camera.release();
            camera = null;
        }

        // 카메라 객체의 takePricture 메서드를 호출하여 사진 촬영
        public boolean capture(Camera.PictureCallback handler) {
            if (camera != null) {
                camera.takePicture(null, null, handler);

                return true;
            } else {
                return false;
            }
        }
    }
}
```

- ﻿카메라 미리보기를 위해 사용되는 서피스뷰의 생성자 부분에는 서피스홀더 객체를 getHolder 메서드로 참조하는 코드와 서피스홀더의 addCallback 메서드로 이 클래스에서 구현된 Callback 객체를 지정하는 코드들이 들어갑니다.

- 서피스뷰가 만들어지면서 호출되는 surfaceCreated 메서드 안에서는 Camera.open 메서드를 이용해 카메라를 오픈하고 이를 통해 참조한 카메라 객체에 서피스홀더 객체를 지정해야 합니다. 카메라 객체에 서피스홀더 객체를 지정하기 위해 setPreviewDisplay 메서드를 사용합니다. 서피스뷰의 크기가 변경되거나 할 때 호출되는 surfaceChanged 메서드 안에서는 startPreview 메서드를 이용해 미리보기를 시작하도록 합니다. 만약 카메라의 파라미터 설정이 필요하다면 setParameters 메서드를 이용해 필요한 파라미터를 설정할 수 있습니다.

- 여기에서는 가로 화면과 세로 화면 각각의 경우에 미리보기 영역의 폭과 높이를 설정하도록 하였으므로 setPreviewSize 메서드를 이용해 각각의 경우에 맞게 폭과 높이를 설정하는 코드가 들어 있습니다. 서피스뷰의 리소스를 해제하면서 호출되는 surfaceDestroyed 메서드 안에서는 카메라 객체의 stopPreview 메서드를 호출하여 미리보기를 끝낸 후 변수를 널값으로 지정하도록 합니다. 메인 액티비티의 버튼을 클릭했을 때 사진을 찍기 위해 호출하는 capture 메서드 안을 보면 카메라 객체의 takePicture 메서드를 호출하여 사진을 찍고 있으며, 이때 PictureCallback 인터페이스를 구현한 객체를 파라미터로 전달함으로써 사진을 찍었을 때 이 객체의 onPictureTaken 메서드가 자동 호출되도록 합니다.

- 지금 만든 앱에서는 CAMERA와 SD 카드를 접근하므로 이 권한을 매니페스트에 추가하고 위험권한에 대한 권한 부여를 요청하는 코드도 추가해야 합니다. 먼저 AndroidManifest.xml 파일을 열고 다음과 같이 권한을 추가합니다.

#### SampleCapture>/app/manifests/AndroidManifest.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    package="org.koreait.capture">

    <uses-permission android:name="android.permission.CAMERA" />
    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
    <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />

    <uses-feature android:name="android.hardware.camera"
            android:required="true" />
    
	... 생략 

</manifest>
```

- 이 권한들은 위험 권한으로 분류되어 있으므로 자동으로 위험 권한 부여를 위한 대화상자를 보여주는 코드를 추가합니다. 먼저 build.gradle(Module: SampleCapture.app) 파일을 열고 자동으로 위험 권한을 부여하기 위한 외부 라이브러리를 추가합니다.

#### ﻿SampleCapture>/Gradle Scripts/build.gradle (Module: SampleCapture.app)

```gradle

... 생략 

dependencies {

	... 생략 
	
    implementation 'com.yanzhenjie:permission:2.0.2'
}
```
 
- 그런 다음 다시 MainActivity.java 파일을 열고 권한 부여를 위한 코드를 추가합니다.

#### SampleCapture>/app/java/org.koreait.capture/MainActivity.java

```java

... 생략

public class MainActivity extends AppCompatActivity {
    CameraSurfaceView cameraView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        
		... 생략

        AndPermission.with(this)
                .runtime()
                .permission(
                        Permission.CAMERA,
                        Permission.READ_EXTERNAL_STORAGE,
                        Permission.WRITE_EXTERNAL_STORAGE)
                .onGranted(new Action<List<String>>() {
                    @Override
                    public void onAction(List<String> permissions) {
                        showToast("허용된 권한 개수: " + permissions.size());
                    }
                })
                .onDenied(new Action<List<String>>() {
                    @Override
                    public void onAction(List<String> permissions) {
                        showToast("거부된 권한 개수 : " + permissions.size());
                    }
                })
                .start();
    }

    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

	... 생략
}
```

- 이제 앱을 실행하면 위험 권한을 부여하기 위한 대화상자가 먼저 표시됩니다. 권한을 허용하면 화면에 카메라 미리보기가 표시됩니다. [사진 찍기] 버튼을 클릭하면 사진도 찍을 수 있습니다. [Alt]를 누르고 화면을 이리저리 움직여 보기도 하세요. 만약 바로 카메라 화면이 나오지 않으면 앱을 다시 실행해 보세요. 찍은 사진은 Photos 앱을 실행한 다음 Pictures 항목에서 확인할 수 있습니다.

![image2](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/12~13%EC%9D%BC%EC%B0%A8(6h)%20-%20%EB%A9%80%ED%8B%B0%EB%AF%B8%EB%94%94%EC%96%B4/2.%20%ED%99%94%EB%A9%B4%EC%97%90%20%EC%B9%B4%EB%A9%94%EB%9D%BC%20%EB%AF%B8%EB%A6%AC%20%EB%B3%B4%EA%B8%B0%20%EB%84%A3%EA%B8%B0/images/image2.png)

> 최신 버전의 에뮬레이터에서는 카메라를 열 때 사용되는 Camera.open() 코드가 정상적으로 동작하지 않는 경우가 있습니다. 다른 앱에서 카메라를 사용 중인 것으로 인식하는 경우인데요. 만약 미리보기가 표시되지 않는다면 이전 버전의 안드로이드 OS로 하여 에뮬레이터를 새로 만든 후 테스트해 보세요.

- 서피스뷰로 카메라 미리보기 화면을 띄워보면 화면이 돌아가 있는 것을 볼 수 있습니다. 이는 미리보기의 기본 모드가 가로 모드(Landscape mode)이기 때문입니다. 카메라 미리보기의 화면이 세로 모드로 보이도록 하고 싶다면 MainActivity 클래스의 내부 클래스로 정의한 CameraSurfaceView 클래스 안의 surfaceCreated 메서드를 수정합니다.

#### SampleCapture>/app/java/org.koreait.capture/MainActivity.java

```java

... 생략 

public class MainActivity extends AppCompatActivity {
	
	... 생략
	
    // SurfaceView 클래스를 상속하고 Callback 인터페이스를 구현하는 새로운 CameraSurfaceView 클래스 정의
    class CameraSurfaceView extends SurfaceView implements SurfaceHolder.Callback {
		
		... 생략

        // 서피스뷰가 만들어질 떄 카메러 객체를 참조한 후 미리보기 화면으로 홀더 객체 생성
        public void surfaceCreated(SurfaceHolder holder) {
            camera = Camera.open();
            setCameraOrientation();
            try {
                camera.setPreviewDisplay(mHolder);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
		... 생략

        public void setCameraOrientation() {
            if (camera == null) {
                return;
            }

            Camera.CameraInfo info = new Camera.CameraInfo();
            Camera.getCameraInfo(0, info);

            WindowManager manager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
            int rotation = manager.getDefaultDisplay().getRotation();  // 회전에 대한 정보 확인하기

            int degrees = 0;
            switch (rotation) {
                case Surface.ROTATION_0: degrees = 0; break;
                case Surface.ROTATION_90: degrees = 90; break;
                case Surface.ROTATION_180: degrees = 180; break;
                case Surface.ROTATION_270: degrees = 270; break;
            }

            int result;
            if (info.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
                result = (info.orientation + degrees) % 360;
                result = (360 - result) % 360;
            } else {
                result = (info.orientation - degrees + 360) % 360;
            }

            // 카메라 객체의 setDisplayOrientation 메서드 호출하기
            camera.setDisplayOrientation(result);
        }
    }
}
```

- ﻿setCameraOrientation 메서드는 onSurfaceCreated 메서드 안에서 호출되는 메서드이며 카메라 미리보기의 방향을 설정합니다. 지금까지 카메라 미리보기 화면을 앱 안에서 보여주고 사진을 찍는 방법에 대해 알아보았습니다.