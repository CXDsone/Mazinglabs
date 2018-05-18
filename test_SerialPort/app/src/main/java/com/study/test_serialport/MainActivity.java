package com.study.test_serialport;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import android_serialport_api.SerialPort;


public class MainActivity extends AppCompatActivity {
    EditText mReceive;
    FileOutputStream mOutputStream;
    FileInputStream mInputStream;
    SerialPort sp;
    int return_time = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //打开按钮事件
        final Button btn_open = (Button) findViewById(R.id.btn_open);
        btn_open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    sp = new SerialPort(new File("/dev/ttyS4"), 38400, 0);
                } catch (SecurityException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    Log.i("test", "打开失败");
                    e.printStackTrace();
                }
                mOutputStream = (FileOutputStream) sp.getOutputStream();
                mInputStream = (FileInputStream) sp.getInputStream();
                Toast.makeText(getApplicationContext(), "串口打开成功", Toast.LENGTH_SHORT).show();
            }
        });

        //发送开锁指令
        final Button btn_openClock1 = (Button) findViewById(R.id.btn_openClock1);
        btn_openClock1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendOpenLock(1, 1);
            }
        });

        final Button btn_openClock2 = (Button) findViewById(R.id.btn_openClock2);
        btn_openClock2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendOpenLock(1, 2);
            }
        });

        final Button btn_openClock3 = (Button) findViewById(R.id.btn_openClock3);
        btn_openClock3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendOpenLock(1, 3);
            }
        });

        final Button btn_openClock4 = (Button) findViewById(R.id.btn_openClock4);
        btn_openClock4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendOpenLock(1, 4);
            }
        });

        final Button btn_openClock5 = (Button) findViewById(R.id.btn_openClock5);
        btn_openClock5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendOpenLock(1, 5);
            }
        });

        final Button btn_openClock6 = (Button) findViewById(R.id.btn_openClock6);
        btn_openClock6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendOpenLock(1, 6);
            }
        });

        final Button btn_openClock7 = (Button) findViewById(R.id.btn_openClock7);
        btn_openClock7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendOpenLock(1, 7);
            }
        });

        final Button btn_openClock8 = (Button) findViewById(R.id.btn_openClock8);
        btn_openClock8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendOpenLock(1, 8);
            }

        });
        //控制LED
        final Button btn_openLED = (Button) findViewById(R.id.btn_openLED);
        btn_openLED.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendLED(1, 1);
            }
        });

        final Button btn_closeLED = (Button) findViewById(R.id.btn_closeLED);
        btn_closeLED.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendLED(1, 0);
            }
        });

        final Button btn_openFan = (Button) findViewById(R.id.btn_openFan);
        btn_openFan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendFan(1, 1);
            }
        });

        final Button btn_closeFan = (Button) findViewById(R.id.btn_closeFan);
        btn_closeFan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendFan(1, 0);
            }
        });


        //读取锁状态
        final Button btn_readClock = (Button) findViewById(R.id.btn_readClock);
        btn_readClock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    String read;
                for(int i = 0 ;i < 500; i++) {
                    try {
                        Thread.sleep(10);
                    }catch (Exception e){
                        Log.d("result","ERROR");
                    }
                    read = sendGetStat(1);
                    return_time++;
                    Log.d("result", read);
                    Log.d("result", String.format("%d",return_time));
                }
            }
        });

        final Button btn_getLoad = (Button) findViewById(R.id.btn_getLoad);
        btn_getLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetLoad();
            }
        });
    }

    /**
     * 计算产生校验码
     * @param data 需要校验的数据
     * @return 校验码
     */
    public static String Make_CRC(byte[] data, int start,int num) {
        byte[] buf = new byte[num];// 存储需要产生校验码的数据
        for (int i = 0; i < num; i++) {
            buf[i] = data[start+i];
        }
        int len = num;
        int crc = 0xFFFF;//16位
        for (int pos = 0; pos < len; pos++) {
            if (buf[pos] < 0) {
                crc ^= (int) buf[pos] + 256; // XOR byte into least sig. byte of
                // crc
            } else {
                crc ^= (int) buf[pos]; // XOR byte into least sig. byte of crc
            }
            for (int i = 8; i != 0; i--) { // Loop over each bit
                if ((crc & 0x0001) != 0) { // If the LSB is set
                    crc >>= 1; // Shift right and XOR 0xA001
                    crc ^= 0xA001;
                } else
                    // Else LSB is not set
                    crc >>= 1; // Just shift right
            }
        }
        String c = Integer.toHexString(crc);
        if (c.length() == 4) {
            c = c.substring(2, 4) + c.substring(0, 2);
        } else if (c.length() == 3) {
            c = "0" + c;
            c = c.substring(2, 4) + c.substring(0, 2);
        } else if (c.length() == 2) {
            c = "0" + c.substring(1, 2) + "0" + c.substring(0, 1);
        }
        return c;
    }
    /**
     * Convert hex string to byte[]
     * @param hexString the hex string
     * @return byte[]
     */
    public static byte[] hexStringToBytes(String hexString) {
        if (hexString == null || hexString.equals("")) {
            return null;
        }
        hexString = hexString.toUpperCase();
        int length = hexString.length() / 2;
        char[] hexChars = hexString.toCharArray();
        byte[] d = new byte[length];
        for (int i = 0; i < length; i++) {
            int pos = i * 2;
            d[i] = (byte)(charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
        }
        return d;
    }

    /**
     * Convert char to byte
     * @param c char
     * @return byte
     */
    private static byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }
    /*
    * 输入字符串w 返回lrc校验部分
    * @param  w=需要传输的信息  ID是地址
    * @return finaldata
    */
    public String Lrc(String w) {
        char[] m = w.toCharArray();
        int x = 0;
        int length = m.length;
        int[] lrcdata = new int[length];
        for (int i = 0; i < length; i++) {
            if (m[i] >= 'A')
                lrcdata[i] = m[i] - 'A' + 10;
            else
                lrcdata[i] = m[i] - '0';
        }
        for (int i = 0; i < length / 2; i++) {
            x += (lrcdata[2 * i] * 16 + lrcdata[2 * i + 1]);
        }
        x = x % 256;
        x = 256 - x;
        String finaldata = Integer.toHexString(x % 256).toUpperCase();
        return finaldata;
    }

    /**
     * 发送读取重量指令
     * @return
     */
    public int GetLoad(){
        byte[] data = new byte[8];
        data[0] = 0x00;
        data[1] = 0x03;
        data[2] = 0x00;
        data[3] = 0x00;
        data[4] = 0x00;
        data[5] = 0x02;
        String buf = Make_CRC(data,0,6);
        byte[] result = hexStringToBytes(buf);
        data[6] = result[0];
        data[7] = result[1];
        try {
            mOutputStream.write(data);
        } catch (IOException e) {
            Log.i("test", "发送失败");
            e.printStackTrace();
            return -1;
        }
        int load = readLoad();
        return load;
    }

    public int readLoad(){
        int Error = -1;
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(50);
                if (mInputStream.available() > 0) {
                    if (mInputStream != null) {
                        byte[] buffer = new byte[2048];
                        int size = mInputStream.read(buffer);
                        if (size > 8){
                            int head = 0;
                            while (head < size-2){
                                if (buffer[head] != 0||buffer[head+1] != 3||buffer[head+2] != 4)
                                {
                                    head++;
                                    continue;
                                }
                                if (size-head > 8){
                                    String crc = Make_CRC(buffer,head,7);
                                    byte[] result = hexStringToBytes(crc);
                                    if (buffer[head+7] == result[0]&&buffer[head+8] == result[1]) {
                                        int load = (buffer[head+4]&0xff)+((buffer[head+3]&0xff)<<8)+((buffer[head+6]&0xff)<<16)+((buffer[head+5]&0xff)<<32);
                                        Log.d("Load","Load");
                                        return load;
                                    }
                                    else{
                                        Log.d("SerialPort","校验码错误");
                                        return Error;
                                    }
                                }
                                else{
                                    return Error;
                                }
                            }
                        }
                    }
                }
            } catch (Exception e) {
                continue;
            }
        }
        return Error;
    }

    /**
     * 发送数据
     *
     * @param sendData
     * @return
     */
    public int UART_Send(String sendData) {
        byte[] mBuffer = (sendData + "\r\n").getBytes();
        try {
            mOutputStream.write(mBuffer);
        } catch (IOException e) {
            Log.i("test", "发送失败");
            e.printStackTrace();
            return -1;
        }
        Toast.makeText(getApplicationContext(), "发送成功", Toast.LENGTH_SHORT).show();
        return 1;
    }


    /**
     * 发送开锁指令
     *
     * @param DID：锁控板地址号
     * @param drawerNu：抽屉号
     */
    public int sendOpenLock(int DID, int drawerNu) {
        StringBuilder drawerData = new StringBuilder();
        for (int i = 0; i < 16; i++) {
            if (drawerNu == i + 1)
                drawerData.append("1");
            else
                drawerData.append("0");
        }
        String str = String.format("%02x", DID).toUpperCase() + "21" + drawerData;
        String sendData = ":" + str + Lrc(str);
        Log.d("data", sendData);
        UART_Send(sendData);
        return 1;
    }

    /**
     * 发送查询锁状态
     *
     * @param DID ：锁控板地址号
     */
    public String sendGetStat(int DID) {
        String str = String.format("%02x", DID).toUpperCase() + "11";
        String sendData = ":" + str + Lrc(str);
        Log.d("data", sendData);
        UART_Send(sendData);
        String result;
        result = readData();
        return result;
    }

    /**
     * 控制LED等
     *
     * @param DID：锁控板地址号
     * @param data：1     开启；0关闭
     */
    public int sendLED(int DID, int data) {
        String str = String.format("%02x", DID) + "41" + String.format("%02x", data);
        String sendData = ":" + str + Lrc(str);
        Log.d("data", sendData);
        UART_Send(sendData);
        return 1;
    }

    /**
     * 控制风扇
     *
     * @param DID：锁控板地址号
     * @param data：1     开启；0关闭
     */
    public int sendFan(int DID, int data) {
        String str = String.format("%02x", DID).toUpperCase() + "31" + String.format("%02x", data);
        String sendData = ":" + str + Lrc(str);
        Log.d("data", sendData);
        UART_Send(sendData);
        return 1;
    }

    /**
     * 接收数据
     * @param
     * @return
     */
    public String readData() {
        String readDatas = null;
        String Error = "-1";
        String lrc;
        byte[] buffer2 ;
        byte[] buffer3 = new byte[2];
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(50);
                if (mInputStream.available() > 0) {
                    if (mInputStream != null) {
                        byte[] buffer = new byte[2048];
                        int size = mInputStream.read(buffer);
                        if (size > 21) {
                            int head = 0;
                            while (head < size) {
                                if (buffer[head] != ':') {
                                    head++;
                                    continue;
                                }
                                if (size - head - 1 > 20) {
                                    buffer2 = new byte[20];
                                    for (int j = 0; j < 20; j++) {
                                        buffer2[j] = buffer[head + j + 1];
                                    }
                                    buffer3[0] = buffer[head + 21];
                                    buffer3[1] = buffer[head + 22];
                                    readDatas = new String(buffer2);
                                    lrc = new String(buffer3);
                                    if (lrc.equals(Lrc(readDatas))) {
                                        return readDatas;
                                    } else {
                                        return Error;
                                    }
                                } else
                                    return Error;
                            }
                            break;
                        } else
                            break;
                    }
                    if (i == 9) {
                        break;
                    }
                }
            }catch(Exception e){
                return Error;
            }
        }
        return Error;
    }
}
