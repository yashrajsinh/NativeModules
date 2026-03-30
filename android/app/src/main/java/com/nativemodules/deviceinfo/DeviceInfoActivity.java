package com.nativemodules.deviceinfo;

import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.nativemodules.R;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Collections;
import java.util.List;

public class DeviceInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_device_info);

        TextView textView = findViewById(R.id.deviceInfoText);
        textView.setText(getDeviceInfo());
    }
    private String getDeviceInfo() {
        StringBuilder sb = new StringBuilder();

        // Device model & manufacturer
        sb.append("Device: ").append(Build.MANUFACTURER).append(" ").append(Build.MODEL).append("\n");
        // Android version
        sb.append("Android Version: ").append(Build.VERSION.RELEASE).append("\n");
        // SDK
        sb.append("SDK: ").append(Build.VERSION.SDK_INT).append("\n");
        // IP address
        sb.append("IP: ").append(getLocalIpAddress()).append("\n");

        return sb.toString();
    }
    private String getLocalIpAddress() {
        try {
            List<NetworkInterface> interfaces = Collections.list(NetworkInterface.getNetworkInterfaces());
            for (NetworkInterface intf : interfaces) {
                List<InetAddress> addrs = Collections.list(intf.getInetAddresses());
                for (InetAddress addr : addrs) {
                    if (!addr.isLoopbackAddress()) {
                        String sAddr = addr.getHostAddress();
                        if (!sAddr.contains(":")) { // ignore IPv6
                            return sAddr;
                        }
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "Unavailable";
    }
}