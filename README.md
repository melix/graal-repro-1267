## Reproducer for https://github.com/oracle/graal/issues/1267

Because [GraalVM Palantir Gradle plugin](https://github.com/palantir/gradle-graal) doesn't support 19.0.0 yet (there has been a change in the layout of the file name **and** it requires an additional `gu install` step),
this reproducer uses `1.0.0-rc16`. However, I have confirmed the same error occurs with `19.0.0`.

It is easy to check if you need to.

To reproduce, run:

`./gradlew nativeImage`

then:

`build/graal/repro`

This will result in the following error:

```
Exception in thread "main" java.lang.RuntimeException: Could not determine the IP addresses for network interface vetha55dc05
	at graal.network.repro.Library.analyzeNetworkInterface(Library.java:45)
	at graal.network.repro.Library.analyzeNetworkInterfaces(Library.java:21)
	at graal.network.repro.Library.main(Library.java:14)
Caused by: java.lang.UnsatisfiedLinkError: java.net.NetworkInterface.isLoopback0(Ljava/lang/String;I)Z [symbol: Java_java_net_NetworkInterface_isLoopback0 or Java_java_net_NetworkInterface_isLoopback0__Ljava_lang_String_2I]
	at com.oracle.svm.jni.access.JNINativeLinkage.getOrFindEntryPoint(JNINativeLinkage.java:145)
	at com.oracle.svm.jni.JNIGeneratedMethodSupport.nativeCallAddress(JNIGeneratedMethodSupport.java:57)
	at java.net.NetworkInterface.isLoopback0(NetworkInterface.java)
	at java.net.NetworkInterface.isLoopback(NetworkInterface.java:411)
	at graal.network.repro.Library.analyzeNetworkInterface(Library.java:29)
```