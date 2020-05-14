# FFmpeg编译文件导入AS项目

## 一、配置项目支持C++

- 在项目根目录新建一个cpp文件夹，里面创建一个CMakeLists.txt文件和一个native-lib.cpp文件

  ```cmake
  #CMakeLists.txt文件
  cmake_minimum_required(VERSION 3.4.1)
  add_library(native-lib
          SHARED
          native-lib.cpp)
  find_library(
          log-lib
          log)
  target_link_libraries(
          native-lib
          ${log-lib})
  ```

  ```c++
  //native-lib.cpp文件
  #include <jni.h>
  #include <string>
  extern "C"
  JNIEXPORT jstring JNICALL
  Java_com_clover_ffmpeglibrary_jni_FFmpegNative_stringFromJNI(JNIEnv *env, jobject thiz) {
       std::string hello = "Hello from C++";
       return env->NewStringUTF(hello.c_str());
  }
  ```

  

- 配置build.gradle文件

  ```groovy
  apply plugin: 'com.android.library'
  android {
      compileSdkVersion 29
      buildToolsVersion "29.0.3"
      defaultConfig {
          minSdkVersion 15
          targetSdkVersion 29
          versionCode 1
          versionName "1.0"
          testInstrumentationRunner "androidx.test.runner.AndroidJUnitRunner"
          consumerProguardFiles 'consumer-rules.pro'
          externalNativeBuild {
              cmake {
                  cppFlags ""
              }
              ndk{
                  abiFilters 'armeabi-v7a', 'arm64-v8a', 'x86', 'x86_64'
              }
          }
      }
      buildTypes {
          release {
              minifyEnabled false
              proguardFiles getDefaultProguardFile('proguard-android-optimize.txt'), 'proguard-rules.pro'
          }
      }
      externalNativeBuild {
          cmake {
              path "src/main/cpp/CMakeLists.txt"
              version "3.10.2"
          }
      }
      sourceSets {
          main {
              jniLibs.srcDirs = ['libs']
          }
      }
  }
  dependencies {
      implementation fileTree(dir: 'libs', include: ['*.jar'])
      implementation 'androidx.appcompat:appcompat:1.1.0'
      testImplementation 'junit:junit:4.12'
      androidTestImplementation 'androidx.test.ext:junit:1.1.1'
      androidTestImplementation 'androidx.test.espresso:espresso-core:3.2.0'
  }
  ```

  

  ## 二、引入ffmpeg到项目中

  - 引入include文件夹，将编译生成的include文件夹拷贝到cpp下。

  - 将对应的so包放到指定的libs下。

  - 配置CMakeLists.txt文件。

    ```cmake
    #CMakeLists.txt文件
    cmake_minimum_required(VERSION 3.4.1)
    
    #添加头文件路径
    include_directories(include)
    #设置FFmpeg库所在路径
    set(FF ${CMAKE_CURRENT_SOURCE_DIR}/../../../libs/${ANDROID_ABI})
    
    add_library(native-lib
            SHARED
            native-lib.cpp)
    #添加avcodec库
    add_library(avcodec
            SHARED
            IMPORTED)
    set_target_properties(avcodec PROPERTIES IMPORTED_LOCATION ${FF}/libavcodec.so)
    
    #添加avdevice库
    add_library(avdevice
            SHARED
            IMPORTED)
    set_target_properties(avdevice PROPERTIES IMPORTED_LOCATION ${FF}/libavdevice.so)
    
    #添加avfilter库
    add_library(avfilter
            SHARED
            IMPORTED)
    set_target_properties(avfilter PROPERTIES IMPORTED_LOCATION ${FF}/libavfilter.so)
    
    #添加avformat库
    add_library(avformat
            SHARED
            IMPORTED)
    set_target_properties(avformat PROPERTIES IMPORTED_LOCATION ${FF}/libavformat.so)
    
    #添加avutil库
    add_library(avutil
            SHARED
            IMPORTED)
    set_target_properties(avutil PROPERTIES IMPORTED_LOCATION ${FF}/libavutil.so)
    
    #添加swresample库
    add_library(swresample
            SHARED
            IMPORTED)
    set_target_properties(swresample PROPERTIES IMPORTED_LOCATION ${FF}/libswresample.so)
    
    #添加swscale库
    add_library(swscale
            SHARED
            IMPORTED)
    set_target_properties(swscale PROPERTIES IMPORTED_LOCATION ${FF}/libswscale.so)
    
    find_library(
            log-lib
            log)
    target_link_libraries(
            native-lib
             avcodec
            avdevice
            avfilter
            avformat
            avutil
            swresample
            swscale
            ${log-lib})
    ```

  - 在native-lib.cpp引用测试

    ```c++
    //native-lib.cpp文件
    #include <jni.h>
    #include <string>
    
    extern "C"
    {
    #include "include/libavcodec/avcodec.h"
    #include "include/libavformat/avformat.h"
    }
    extern "C"
    JNIEXPORT jstring JNICALL
    Java_com_clover_ffmpeglibrary_jni_FFmpegNative_stringFromJNI(JNIEnv *env, jobject thiz) {
         std::string hello = "Hello from C++";
         hello += avcodec_configuration();
         return env->NewStringUTF(hello.c_str());
    }
    ```

    

  