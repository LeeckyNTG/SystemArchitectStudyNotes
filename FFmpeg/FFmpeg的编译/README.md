# FFmpeg4.2.2的编译

## 一、环境准备

- 操作系统：Ubuntu16.04
- NDK版本：android-ndk-r20b

## 二、编译脚本

```shell
#!/bin/bash
make clean
 
export NDK=/home/itc/Desktop/ffmpeg/android-ndk-r20b
TOOLCHAIN=$NDK/toolchains/llvm/prebuilt/linux-x86_64/
API=21
 
function build_android
{
    echo "Compiling FFmpeg for $CPU"
    ./configure \
        --prefix=$PREFIX \
        --enable-neon \
        --enable-hwaccels \
        --enable-gpl \
        --enable-postproc \
        --enable-shared \
        --enable-jni \
        --enable-mediacodec \
        --disable-decoders \
        --enable-decoder=h264_mediacodec \
        --enable-decoder=vp9 \
        --enable-decoder=h264 \
        --enable-decoder=mpeg4 \
        --enable-decoder=aac \
        --enable-decoder=aac_latm \
        --enable-decoder=mjpeg \
        --enable-decoder=png \
        --enable-decoder=mpeg4_mediacodec \
        --disable-encoders \
        --enable-encoder=vp9_vaapi
        --enable-encoder=h264_nvenc
        --enable-encoder=h264_v4l2m2m
        --enable-encoder=hevc_nvenc \
        --disable-demuxers \
        --enable-demuxer=rtsp
        --enable-demuxer=rtp
        --enable-demuxer=flv
        --enable-demuxer=h264 \
        --disable-muxers \
        --enable-muxer=rtsp
        --enable-muxer=rtp
        --enable-muxer=flv
        --enable-muxer=h264 \
        --disable-parsers \
        --enable-parser=mpeg4video
        --enable-parser=aac
        --enable-parser=h264
        --enable-parser=vp9 \
        --disable-protocols \
        --enable-protocol=rtmp
        --enable-protocol=rtp
        --enable-protocol=tcp
        --enable-protocol=udp \
        --disable-bsfs \
        --disable-indevs --enable-indev=v4l2 \
        --disable-outdevs \
        --disable-filters \
        --disable-postproc \
        --disable-static \
        --disable-doc \
        --disable-ffmpeg \
        --disable-ffplay \
        --disable-ffprobe \
        --enable-avdevice \
        --disable-doc \
        --disable-symver \
        --cross-prefix=$CROSS_PREFIX \
        --target-os=android \
        --arch=$ARCH \
        --cpu=$CPU \
        --cc=$CC \
        --cxx=$CXX \
        --enable-cross-compile \
        --sysroot=$SYSROOT \
        --extra-cflags="-Os -fpic $OPTIMIZE_CFLAGS" \
        --extra-ldflags="$ADDI_LDFLAGS" \
        $ADDITIONAL_CONFIGURE_FLAG
        
        make clean
        make -j4
        make install
        echo "The Compilation of FFmpeg for $CPU is completed"
         
}
 #armv8-a
 ARCH=arm64
 CPU=armv8-a
 CC=$TOOLCHAIN/bin/aarch64-linux-android$API-clang
 CXX=$TOOLCHAIN/bin/aarch64-linux-android$API-clang++
 SYSROOT=$NDK/toolchains/llvm/prebuilt/linux-x86_64/sysroot
 CROSS_PREFIX=$TOOLCHAIN/bin/aarch64-linux-android-
 PREFIX=$(pwd)/android/$CPU
 OPTIMIZE_CFLAGS="-march=$CPU"
 build_android
    
 #armv7-a
 ARCH=arm
 CPU=armv7-a
 CC=$TOOLCHAIN/bin/armv7a-linux-androideabi$API-clang
 CXX=$TOOLCHAIN/bin/armv7a-linux-androideabi$API-clang++
 SYSROOT=$NDK/toolchains/llvm/prebuilt/linux-x86_64/sysroot
 CROSS_PREFIX=$TOOLCHAIN/bin/arm-linux-androideabi-
 PREFIX=$(pwd)/android/$CPU
 OPTIMIZE_CFLAGS="-mfloat-abi=softfp -mfpu=vfp -marm -march=$CPU "
 build_android

#x86
ARCH=x86
CPU=x86
CC=$TOOLCHAIN/bin/i686-linux-android$API-clang
CXX=$TOOLCHAIN/bin/i686-linux-android$API-clang++
SYSROOT=$NDK/toolchains/llvm/prebuilt/linux-x86_64/sysroot
CROSS_PREFIX=$TOOLCHAIN/bin/i686-linux-android-
PREFIX=$(pwd)/android/$CPU
OPTIMIZE_CFLAGS="-march=i686 -mtune=intel -mssse3 -mfpmath=sse -m32"
build_android

#x86_64
ARCH=x86_64
CPU=x86-64
CC=$TOOLCHAIN/bin/x86_64-linux-android$API-clang
CXX=$TOOLCHAIN/bin/x86_64-linux-android$API-clang++
SYSROOT=$NDK/toolchains/llvm/prebuilt/linux-x86_64/sysroot
CROSS_PREFIX=$TOOLCHAIN/bin/x86_64-linux-android-
PREFIX=$(pwd)/android/$CPU
OPTIMIZE_CFLAGS="-march=$CPU -msse4.2 -mpopcnt -m64 -mtune=intel"
build_android
```

