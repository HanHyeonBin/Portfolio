package com.example.bluetoothpairinglist;

import android.Manifest;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Set;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_ENABLE_BT = 3;

    // 반환 인텐트 정보(Return Intent extra)
    public static String EXTRA_DEVICE_ADDRESS = "device_address";

    // 로컬 블루투스 어뎁터 장치 (Member fields)
    private BluetoothAdapter mBtAdapter;

    // 새롭게 발견된 장치 관리 리스트 (Newly discovered devices)
    private ArrayAdapter<String> mNewDevicesArrayAdapter;

    private ArrayAdapter<String> pairedDevicesArrayAdapter;

    private Button scanButton = null;
    private Button btnDiscoverable = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        setContentView(R.layout.activity_main);

        // 사용자가 취소한 경우 처리...
        //setResult(Activity.RESULT_CANCELED);

        // 장치 검색을 위한 버튼 초기화
        scanButton = (Button) findViewById(R.id.button_scan);
        btnDiscoverable = (Button) findViewById(R.id.btnDiscoverable);

        scanButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                doDiscovery();
                v.setVisibility(View.GONE);
            }
        });

        btnDiscoverable.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ensureDiscoverable();
            }
        });

        // Array adapter 초기화.
        // 1. 이미 페어링된 장치용
        // 2. 새로 발견한 장치용
        pairedDevicesArrayAdapter =
                new ArrayAdapter<String>(this, R.layout.device_name);
        mNewDevicesArrayAdapter = new ArrayAdapter<String>(this, R.layout.device_name);

        // 페어링 된 장치를 위한 ListView 찾기/설정
        ListView pairedListView = (ListView) findViewById(R.id.paired_devices);
        pairedListView.setAdapter(pairedDevicesArrayAdapter);
        pairedListView.setOnItemClickListener(mDeviceClickListener);

        // 검색 및 새로 찾은 장치를 위한 ListView 설정
        ListView newDevicesListView = (ListView) findViewById(R.id.new_devices);
        newDevicesListView.setAdapter(mNewDevicesArrayAdapter);
        newDevicesListView.setOnItemClickListener(mDeviceClickListener);

        // 로컬 Bluetooth 어뎁터 가져오기...
        mBtAdapter = BluetoothAdapter.getDefaultAdapter();


    }

    // BluetoothAdapter로 장치 검색 시작
    private void doDiscovery() {
        // 제목에 스캐닝 표시
        //setProgressBarIndeterminateVisibility(true);
        setTitle("장치 스캐닝 중...");

        // 새 장치의 부제목 표시
        findViewById(R.id.title_new_devices).setVisibility(View.VISIBLE);

        // 이미 장치를 발견한 경우 스캐닝 중지
        if (mBtAdapter.isDiscovering()) {
            mBtAdapter.cancelDiscovery();
        }

        // BluetoothAdapter로 검색 요청
        mBtAdapter.startDiscovery();
    }

    // ListView의 모든 장치들을 위한 on-click 리스너
    private AdapterView.OnItemClickListener mDeviceClickListener = new AdapterView.OnItemClickListener() {
        public void onItemClick(AdapterView<?> av, View v, int arg2, long arg3) {
            // 연결 작업을 위해 검색을 취소한다.
            mBtAdapter.cancelDiscovery();

            // 보기에서 마지막 17 문자의 장치 MAC 주소를 가져옵니다.
            String info = ((TextView) v).getText().toString();
            String address = info.substring(info.length() - 17);

            // 목록에서 선택한 값을 토스트로 보여준다.
            Toast.makeText(getApplicationContext(), address.toString(), Toast.LENGTH_SHORT).show();

            //  리스트에서 선택한 장치의 정보를 돌려주기 위한 인텐트와 반환값을 설정하는 코드이다.
            // 이부분을 우선 필요 없기에 주석처리하였으며 향후 리스트창을 요청한 쪽으로 반환할 때 사용한다.
            // MAC 주소를 포함한 result intent 객체 생성한다.

            Intent intent = new Intent(getApplicationContext(), ChatActivity.class);
            intent.putExtra(EXTRA_DEVICE_ADDRESS, address);

            startActivity(intent);

            // intent에 result를 설정하고 Activity를 종료한다.
            //setResult(Activity.RESULT_OK, intent);
            //finish();
        }
    };

    // 장치 검색을 통해 검색된 장치의 광고를 수신하고 제목을 변경할 BR 수신기
    private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();

            // 검색으로 장치를 찾을 때
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                // Intent에서 블루투스 장치 객체 가져온다.
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                // 이미 페어링 된 경우 목록에 있기 때문에 건너뛴다.
                if (device.getBondState() != BluetoothDevice.BOND_BONDED) {
                    mNewDevicesArrayAdapter.add(device.getName() + "\n" + device.getAddress());
                }
                // 검색이 완료되면 Activity 의 제목을 변경한다.
            } else if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action)) {
                setProgressBarIndeterminateVisibility(false);

                setTitle("연결할 장치를 선택하세요!");
                if (mNewDevicesArrayAdapter.getCount() == 0) {
                    String noDevices = "검색된 장치가 없습니다.".toString();
                    setTitle(noDevices);
                    scanButton.setVisibility(View.VISIBLE);
                    //mNewDevicesArrayAdapter.add(noDevices);
                }
            }
        }
    };

    @Override
    protected void onResume() {
        super.onResume();

        // 현재 페어링된 장치 세트 가져오기...
        Set<BluetoothDevice> pairedDevices = mBtAdapter.getBondedDevices();

        pairedDevicesArrayAdapter.clear();

        // 페어링 된 장치가 있는 경우 각 장치를 ArrayAdapter에 추가한다.
        if (pairedDevices.size() > 0) {
            findViewById(R.id.title_paired_devices).setVisibility(View.VISIBLE);
            for (BluetoothDevice device : pairedDevices) {
                pairedDevicesArrayAdapter.add(device.getName() + "\n" + device.getAddress());
            }
        } else {
            String noDevices = "페어링 된 장치가 없습니다.".toString();
            pairedDevicesArrayAdapter.add(noDevices);
        }
    }

    @Override
    public void onStart() {
        super.onStart();

        // 안드로이드 6.0 이상부터 블루투스 사용을 위해 위치 퍼미션을 추가로 설정해야한다.
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
        }

        // 블루투스가 꺼져 있는 경우 활성화하도록 요청한다.
        // setupChat()은 onActivityResult 중에 호출된다.
        if (!mBtAdapter.isEnabled()) {
            Intent enableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            //startActivityForResult(enableIntent, REQUEST_ENABLE_BT);
            startActivity(enableIntent);
            // Otherwise, setup the chat session
        }

        // 장치 발견시 브로드 캐스트로 등록한다.
//        IntentFilter filter = new IntentFilter();
//        filter.addAction(BluetoothDevice.ACTION_FOUND);
//        //filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_STARTED);
//        filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        registerReceiver(mReceiver, filter);

        // 검색이 완료되면 브로드 캐스트에 등록한다.
        filter = new IntentFilter(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
        registerReceiver(mReceiver, filter);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        // 장치 검색을 더이상 수행하고 있지 않는지 확인 검색 중단
        if (mBtAdapter != null) {
            mBtAdapter.cancelDiscovery();
        }

        // Unregister broadcast listeners
        this.unregisterReceiver(mReceiver);
    }

    // 60초 동안 현재 장치의 검색을 허용한다.
    private void ensureDiscoverable() {
        if (mBtAdapter.getScanMode() != BluetoothAdapter.SCAN_MODE_CONNECTABLE_DISCOVERABLE) {
            Intent discoverableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
            discoverableIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 60);
            startActivity(discoverableIntent);
        }
    }
}
