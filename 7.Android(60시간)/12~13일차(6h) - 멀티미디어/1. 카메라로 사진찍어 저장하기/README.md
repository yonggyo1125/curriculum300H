# 카메라로 사진 찍어 저장하기

- 멀티미디어 기능에서 가장 기본적인 기능을 꼽는다면 카메라 기능이라고 할 수 있습니다. 카메라는 단순히 사진을 찍는 용도로 사용되지만 최근에는 카메라 미리보기 화면에 여러 가지 정보를 더 표시하거나 카메라로 보는 영상이나 이미지를 앱의 다른 기능에 활용하는 경우도 많습니다. 예를 들어, 카메라로 바코드를 찍으면 바코드 정보를 추출할 수 있는 바코드 리더기(Barcode Reader)를 만들 수 있습니다. 또는 카메라 미리보기 기능으로 주변에 있는 커피숍 정보나 매장의 쿠폰 정보를 함께 보여주는 증강현실(AR:Augmented Reality) 앱은 단순한 흥미 이상의 유용한 생활밀착형 기능을 제공합니다. 이렇게 다양한 목적으로 사용되는 카메라 기능에 익숙해지려면 가장 단순한 사용 방법을 먼저 알아보는것이 좋습니다.

- 카메라로 사진을 찍기 위해 사용되는 방법은 크게 두 가지로 나눌 수 있습니다.
	- 인텐트로 단말의 카메라 앱을 실행한 후 결과 사진을 받아 처리하기
	- 앱 화면에 카메라 미리보기를 보여주고 직접 사진을 찍어 처리하기
- 스마트폰 단말에는 카메라 앱이 미리 설치되어 있는데 이 앱을 사용하면 가장 간단하게 다른 기능의 앱을 구현할 수 있습니다. 단말의 카메라 앱은 다른 개발자가 미리 만들어 설치해둔 것이므로 여러분이 만들려는 앱에서 카메라 앱의 화면을 띄우려면 인텐트를 만들어 시스템에 요청하면 됩니다.

- 지금부터 인텐트를 사용해서 단말의 카메라 앱을 실행한 후 결과 사진을 받아 처리하는 방법을 알아보겠습니다. 이번에 만들 앱은 사용자가 화면의 버튼을 클릭했을 때 카메라 앱의 화면을 띄웁니다. 그리고 카메라 앱으로 사진을 찍은 후 원래 화면으로 돌아오면 찍은 사진을 화면에 보여 주게 할 것입니다. SampleCaptureIntent라는 새 프로젝트를 만들고 패키지 이름은 org.koreait.captureintent로 입력합니다. 프로젝트 창이 뜨면 activity_main.xml 파일을 열고 다음과 같은 화면을 만듭니다.

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
        android:layout_gravity="center"
        android:text="사진찍기" />

    <ImageView
        android:id="@+id/imageView"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        app:srcCompat="@mipmap/ic_launcher" />
    
</LinearLayout>
```

![image1](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/12~13%EC%9D%BC%EC%B0%A8(6h)%20-%20%EB%A9%80%ED%8B%B0%EB%AF%B8%EB%94%94%EC%96%B4/1.%20%EC%B9%B4%EB%A9%94%EB%9D%BC%EB%A1%9C%20%EC%82%AC%EC%A7%84%EC%B0%8D%EC%96%B4%20%EC%A0%80%EC%9E%A5%ED%95%98%EA%B8%B0/images/image1.png)

- ﻿디자인 화면에서 최상위 레이아웃을 LinearLayout으로 변경하고 orientation 속성 값을 vertical로 설정합니다. 버튼을 하나 추가하고 '사진 찍기'라는 글자가 보이게 만든 후 화면 상단의 가운데 위치하도록 합니다. 버튼의 아래쪽에는 이미지뷰를 추가하고 아래쪽 공간을 꽉 채우세요. 화면을 만들었다면 MainActivity.java 파일을 열고 소스 코드를 입력합니다. 화면에 있는 버튼을 클릭했을 때 단말의 카메라 앱을 띄우도록 하고 카메라 앱에서 사진을 찍고 돌아왔을 때 이미지뷰에 사진을 보여주도록 할 것입니다.

#### SampleCapturelntent>/app/java/org.koreait.captureintent/MainActivity.java

```java
package org.koreait.captureintent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;
import java.net.URI;

public class MainActivity extends AppCompatActivity {
    ImageView imageView;

    File file;
    Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imageView);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                takePicture();
            }
        });
    }

    public void takePicture() {
        try {
            file = createFile();
            if (file.exists()) {
                file.delete();
            }

            file.createNewFile();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (Build.VERSION.SDK_INT >= 24) {
            uri = FileProvider.getUriForFile(this, this.getPackageName(), file);
        } else {
            uri = Uri.fromFile(file);
        }

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);

        startActivityForResult(intent, 101);
    }

    private File createFile() {
        String filename = "capture.jpg";
        File outFile = new File(getFilesDir(), filename);
        Log.d("Main", "File path : " + outFile.getAbsolutePath());

        return outFile;
    }
}
```

- 버튼을 클릭하면 takePicture 메서드를 호출합니다. 이 메서드가 호출되면 먼저 파일을 만드는데 이 파일은 카메라 앱에서 사진을 찍은 후에 그 결과물을 저장할 파일입니다. 파일을 만드는 코드는 createFile 메서드로 분리했으며, capture.jpg라는 이름으로 지정했습니다. 그런데 이렇게 만든 파일을 카메라 앱이 사용할 때는 다른 앱에서 파일을 공유해야 하므로 내용 제공자(Content Provider)를 만들어 해당 폴더를 공유할 수 있게 해야 합니다.

> 안드로이드 버전 7.0 이후부터는 file:// 로 시작하는 Uri 정보를 다른 앱에서 접근할 수 없으며, 반드시 content://로 시작하는 내용 제공자를 사용하도록 바뀌었습니다.

- 먼저 /app/res 폴더 안에 xml 폴더를 먼저 만들고 external.xml 파일을 추가합니다. /app/res 폴더 안에 새로운 폴더를 만들려면 왼쪽 프로젝트 창의/app/res 폴더 위에서 마우스 오른쪽 버튼을 누르고[New → Directory] 메뉴를 선택합니다. 폴더명을 입력하라는 대화상자가 보이면 xml을 입력합니다. 새로운 폴더가 만들어졌다면 다시 그 폴더 위에서 마우스 오른쪽 버튼을 눌러 [New → XML resource file] 메뉴를 선택합니다. File name: 란에는 external.xml을 입력하고 Root element: 란에는 paths를 입력한 후 [OK]를 누릅니다.

![image2](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/12~13%EC%9D%BC%EC%B0%A8(6h)%20-%20%EB%A9%80%ED%8B%B0%EB%AF%B8%EB%94%94%EC%96%B4/1.%20%EC%B9%B4%EB%A9%94%EB%9D%BC%EB%A1%9C%20%EC%82%AC%EC%A7%84%EC%B0%8D%EC%96%B4%20%EC%A0%80%EC%9E%A5%ED%95%98%EA%B8%B0/images/image2.png)

- extenal.xml 파일 안에 있는 \<paths\> 태그는 \<cache-path\>, \<files-path\>, \<external-files-path\> 태그를 포함하고 있으며 이는 앱의 cache 폴더, files 폴더, externalFiles 폴더를 접근할 수 있도록 허용합니다.

#### SampleCaptureIntent>/app/res/xml/external.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<paths xmlns:android="http://schemas.android.com/apk/res/android">
    <cache-path name="cache" path="/" />
    <files-path name="files" path="/" />
    <external-files name="external_files" path="." />
</paths>
```
- 그다음 AndroidManifest.xml 파일을 열고 \<provider\> 태그로 내용 제공자를 추가합니다. 내용 제공자는 androidx 패키지 안에 들어있는 FileProvider를 사용합니다.

#### SampleCaptureIntent>/app/manifests/AndroidManifest.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    package="org.koreait.captureintent">

		... 생략

        <provider
            android:name="androidx.core.content.FileProvider"
            android:authorities="${applicationId}"
            android:exported="false"
            android:grantUriPermissions="true">
            <meta-data
                android:name="android.support.FILE_PROVIDER_PATHS"
                android:resource="@xml/external" />
        </provider>
        
    </application>

</manifest>
```

- \<provider\> 태그 안에는 name 속성이 들어 있고 androidx.core.content.FileProvider 클래스를 지정하고 있습니다. 이것은 FileProvider로 특정 폴더를 공유하는 데 사용하는 내용 제공자입니다. authorities 속성에 설정한 값은 이 앱의 패키지 이름입니다. \<meta-data\> 태그 안에는 name과 resource 속성이 들어가며 resource 속성 값으로 /app/res/xml 폴더 안에 만들었던 external.xml 파일을 지정합니다. 이때 파일 확장자는 제외하므로 @xml/external 값으로 설정됩니다.

- 이제 이렇게 추가한 내용 제공자를 사용해서 찍은 사진을 저장할 파일 위치를 지정하는 코드 부분(MainActivity.java의 takePicture 메서드)을 이해할 수 있습니다. 다음을 눈으로 읽어보고 이해되는지 스스로 점검해 보세요.

#### SampleCaptureIntent>/app/java/org.koreait.capture.intent/MainActivity.java

```java
	
	... 생략 
	
		if (Build.VERSION.SDK_INT >= 24) {
            uri = FileProvider.getUriForFile(this, this.getPackageName(), file);
        } else {
            uri = Uri.fromFile(file);
        }

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);

        startActivityForResult(intent, 101);
	
	... 생략
	
```

- FileProvider.getUriForFile 메서드를 사용하면 카메라 앱에서 공유하여 사용할 수 있는 파일 정보를 Uri 객체로 만들 수 있습니다. Uri 객체는 MediaStore.EXTRA_OUTPUT 키를 사용해서 인텐트에 부가 데이터로 추가됩니다.

- 인텐트 객체를 만들었다면 startActivityForResult 메서드를 이용해서 시스템으로 인텐트 객체를 전달합니다. 단말의 카메라 앱을 띄워달라는 액션 정보는 MediaStore.ACTION_IMAGE_CAPTURE입니다. 인텐트 객체를 만들어 카메라 앱을 실행한 후 사진을 찍고 나면 카메라 앱의 액티비티를 닫게 되는﻿데 그때 응답을 받는 부분은 onActivityResult 메서드입니다. onActivityResult 메서드를 추가하고 다음 코드를 입력합니다.

#### SampleCapturelntent>/app/java/org.koreait.capture.intent/MainActivity.java

```java

... 생략 

public class MainActivity extends AppCompatActivity {
    
	... 생략

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        
        if (requestCode == 101 && resultCode == RESULT_OK) {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 8;
            // 이미지 파일을 Bitmap 객체로 만들기
            Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath(), options);

            imageView.setImageBitmap(bitmap);  // 이미지뷰에 Bitmap 설정하기
        }
    }

    public void takePicture() {
		
		... 생략
		
    }

	... 생략 
	
}
```

- onActivityResult 메서드가 호출되면 카메라 앱에서 찍은 사진을 파일에서 확인할 수 있습니다. 파일은 capture.jpg라는 이름으로 지정했으므로 이 파일을 읽어 들여 이미지뷰에 설정합니다. 이미지 파일을 읽어 들여 이미지뷰에 설정하려면 먼저 파일을 비트맵 객체로 만듭니다. 비트맵 객체는 메모리에 만들어지는 이미지라고 생각할 수 있으며, 비트맵 객체를 만들 때는 어떤 비율로 축소하여 만들 것인지를 지정할 수 있습니다. 일반적으로 카메라 해상도가 높은 경우 비트맵 객체의 크기도 커지므로 적당한 비율로 축소하여 만들게 됩니다. 여기에서는 1/8 크기로 축소했으며 이를 위해 지정하는 옵션은 inSampleSize라는 이름으로 BitmapFactory.Options 객체에 설정되어 있습니다. 비트맵 객체로 만들 때는 BitmapFactory 클래스의 decodeFile 메서드를 호출하면 되고 비트맵 객체로 만들어진 파일﻿은 이미지뷰에 설정할 수 있습니다.

- 이제 앱을 실행하고 [사진 찍기] 버튼을 누르면 단말의 카메라 앱이 실행됩니다. 카메라 앱이 처음 실행될 때는 카메라 앱에서 추가적인 권한을 요청하거나 카메라 초기 설정을 요구할 수도 있습니다. 카메라 앱에서 사진을 찍고 [저장] 버튼을 누르면 원래의 메인 액티비티 화면으로 돌아오면서 화면에 사진이 보이게 됩니다.

![image3](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/12~13%EC%9D%BC%EC%B0%A8(6h)%20-%20%EB%A9%80%ED%8B%B0%EB%AF%B8%EB%94%94%EC%96%B4/1.%20%EC%B9%B4%EB%A9%94%EB%9D%BC%EB%A1%9C%20%EC%82%AC%EC%A7%84%EC%B0%8D%EC%96%B4%20%EC%A0%80%EC%9E%A5%ED%95%98%EA%B8%B0/images/image3.png)

![image4](https://raw.githubusercontent.com/yonggyo1125/curriculum300H/main/7.Android(60%EC%8B%9C%EA%B0%84)/12~13%EC%9D%BC%EC%B0%A8(6h)%20-%20%EB%A9%80%ED%8B%B0%EB%AF%B8%EB%94%94%EC%96%B4/1.%20%EC%B9%B4%EB%A9%94%EB%9D%BC%EB%A1%9C%20%EC%82%AC%EC%A7%84%EC%B0%8D%EC%96%B4%20%EC%A0%80%EC%9E%A5%ED%95%98%EA%B8%B0/images/image4.png)


