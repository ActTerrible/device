package mac.yk.devicemanagement.controller.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Observable;
import java.util.Observer;

import butterknife.BindView;
import butterknife.ButterKnife;
import mac.yk.devicemanagement.MyApplication;
import mac.yk.devicemanagement.R;
import mac.yk.devicemanagement.bean.Device;
import mac.yk.devicemanagement.util.L;

/**
 * Created by mac-yk on 2017/3/3.
 */

public class fragDetail extends Fragment implements Observer{

    @BindView(R.id.deviceName)
    TextView deviceName;
//    @BindView(R.id.lunbo)
//    lunboView lunbo;
//    @BindView(R.id.zhishi)
//    zhishiqiView zhishi;
    @BindView(R.id.detail)
    TextView detail;
    Observer observer;
    Device device;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_detail, container, false);
        ButterKnife.bind(this, view);
        observer=this;
        device=MyApplication.getDevice();
        L.e("main","fragdetail:"+device.toString());
        deviceName.setText(device.getName());
        detail.setText(device.toString());
        device.addObserver(observer);
        return view;
    }




    @Override
    public void update(Observable o, Object arg) {
        device= (Device) o;
        detail.setText(device.toString());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        device.deleteObserver(this);
    }
}
