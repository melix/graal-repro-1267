/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package graal.network.repro;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

public class Library {

    public static void main(String... args) throws SocketException {
        new Library().analyzeNetworkInterfaces();
    }

    private void analyzeNetworkInterfaces() throws SocketException {
        Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
        if (interfaces != null) {
            while (interfaces.hasMoreElements()) {
                analyzeNetworkInterface(interfaces.nextElement());
            }
        }
    }


    private void analyzeNetworkInterface(NetworkInterface networkInterface) {
        try {
            boolean isLoopbackInterface = networkInterface.isLoopback();

            Enumeration<InetAddress> candidates = networkInterface.getInetAddresses();
            while (candidates.hasMoreElements()) {
                InetAddress candidate = candidates.nextElement();
                if (isLoopbackInterface) {
                    if (candidate.isLoopbackAddress()) {
                        System.out.println("Loopback = " + candidate);
                    }
                } else {
                    if (!candidate.isLoopbackAddress()) {
                        System.out.println("Remote = " + candidate);
                    }
                }
            }
        } catch (Throwable e) {
            throw new RuntimeException(String.format("Could not determine the IP addresses for network interface %s", networkInterface.getName()), e);
        }
    }
}
