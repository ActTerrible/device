package mac.yk.devicemanagement.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import mac.yk.customdialog.CustomDialog;
import mac.yk.devicemanagement.R;
import mac.yk.devicemanagement.adapter.FormTotalAdapter;
import mac.yk.devicemanagement.application.MyMemory;
import mac.yk.devicemanagement.net.ApiWrapper;
import mac.yk.devicemanagement.net.ServerAPI;
import mac.yk.devicemanagement.util.ConvertUtils;
import mac.yk.devicemanagement.util.L;
import rx.Subscriber;

import static com.bumptech.glide.gifdecoder.GifHeaderParser.TAG;

/**
 * Created by mac-yk on 2017/5/10.
 */

public class ScrapCountFragment extends BaseFragment {
    @BindView(R.id.list)
    ListView lv;
    ArrayList<ArrayList<String>> data;

    int shouchitai;
    int guding;
    int yidong;
    int qukongqi;

    String year = "all";
    CustomDialog dialog;
    @BindView(R.id.add_from)
    Button addFrom;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_count, container, false);
        ButterKnife.bind(this, view);
        L.e(TAG,"scrap on create");
        addFrom.setVisibility(View.GONE);
        dialog= CustomDialog.create(getContext(),"加载中...",false,null);
        initView();
        setHasOptionsMenu(true);
        return view;
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.menu_year, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==android.R.id.home){
            return true;
        }
        if (item.getItemId() == R.id.all) {
            year = "all";
        } else if(item.getItemId()==android.R.id.home||item.getItemId()==R.id.action_capture){
            return true;
        }else {
            year = String.valueOf(item.getTitle());
        }
        L.e(TAG,"select"+year);
        if (data!=null){
            data.clear();
        }
        EventBus.getDefault().post(year+"年报废统计");
        initView();
        return true;
    }

    private void initView() {
        EventBus.getDefault().post(year+"年报废统计");
        dialog.show();
        data = new ArrayList<>();
        final ArrayList<String> list = new ArrayList<>();
        String[] arra = new String[]{"顺号", "单位", "手持电台", "固定机控器", "移动机控器", "区控器"};
        list.addAll(ConvertUtils.array2List(arra));
        data.add(list);
        ApiWrapper<ServerAPI> wrapper = new ApiWrapper<>();
        wrapper.targetClass(ServerAPI.class).getAPI().getScrapCount(MyMemory.getInstance().getUser().getUnit(), year, "报废")
                .compose(wrapper.<ArrayList<String[]>>applySchedulers())
                .timeout(30, TimeUnit.SECONDS)
                .subscribe(new Subscriber<ArrayList<String[]>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(ArrayList<String[]> strings) {
                        dialog.dismiss();
                        for (int i = 0; i < strings.size(); i++) {
                            ArrayList<String> list = new ArrayList<String>();
                            list.add(i + 1 + "");
                            list.add(ConvertUtils.getUnitName(i + 1));
                            list.addAll(ConvertUtils.array2List(strings.get(i)));
                            shouchitai += Integer.parseInt(strings.get(i)[0]);
                            guding += Integer.parseInt(strings.get(i)[1]);
                            yidong += Integer.parseInt(strings.get(i)[2]);
                            qukongqi += Integer.parseInt(strings.get(i)[3]);
                            L.e(i + "", list.toString());
                            data.add(list);
                        }

                        ArrayList<String> list1 = new ArrayList<String>();
                        list1.add("");
                        list1.add("合计");
                        list1.add(String.valueOf(shouchitai));
                        list1.add(String.valueOf(guding));
                        list1.add(String.valueOf(yidong));
                        list1.add(String.valueOf(qukongqi));
                        data.add(list1);
                        lv.setAdapter(new FormTotalAdapter(getContext(), data));

                    }
                });

    }
}
