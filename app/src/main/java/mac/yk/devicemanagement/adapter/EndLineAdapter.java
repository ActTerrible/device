package mac.yk.devicemanagement.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import mac.yk.devicemanagement.R;
import mac.yk.devicemanagement.bean.EndLine;
import mac.yk.devicemanagement.util.ConvertUtils;

/**
 * Created by mac-yk on 2017/7/18.
 */

public class EndLineAdapter extends RecyclerView.Adapter<EndLineAdapter.LineHolder> {
    Context context;
    ArrayList<EndLine> lines;



    public EndLineAdapter(Context context, ArrayList<EndLine> lines) {
        this.context = context;
        this.lines = lines;
    }

    @Override
    public LineHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_end_line, parent, false);
        LineHolder holder = new LineHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(LineHolder holder, int position) {

        EndLine line = lines.get(position);
        if (checkStatus(line) == false) {
            holder.ll.setBackgroundColor(context.getResources().getColor(R.color.red));
        }else{
            holder.ll.setBackgroundColor(context.getResources().getColor(R.color.gray2));
        }
        //float的转换
        holder.id.setText(position + 1 + "");
        holder.sensor1.setText(convert(line.getS1()));
        holder.sensor2.setText(convert(line.getS2()));
        holder.battery.setText(String.valueOf(line.getBattery()));
        String s= ConvertUtils.Date2String(new Date(line.getTime()));
        holder.time.setText(s);
        holder.radio.setText(convert(line.getRadio_station()));
        holder.temperature.setText(line.getTemperature() + "");
        holder.power.setText(String.valueOf(line.getPower()));
    }

    private boolean checkStatus(EndLine line) {
        if (line.getS1() == 0 || line.getRadio_station() == 0 || line.getS2() == 0
//                ||line.getBattery()<12.5
                ) {
            return false;
        } else {
            return true;
        }
    }

    String convert(int i) {
        if (i == 1) {
            return "√";
        } else {
            return "×";
        }
    }

    public void clear(){
        lines.clear();
    }

    @Override
    public int getItemCount() {
        return lines.size();
    }

    public class LineHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.id)
        public TextView id;
        @BindView(R.id.temperature)
        TextView temperature;
        @BindView(R.id.radio)
        TextView radio;
        @BindView(R.id.sensor1)
        TextView sensor1;
        @BindView(R.id.sensor2)
        TextView sensor2;
        @BindView(R.id.battery)
        TextView battery;
        @BindView(R.id.time)
        TextView time;
        @BindView(R.id.ll)
        LinearLayout ll;
        @BindView(R.id.card)
        CardView card;
        @BindView(R.id.battery_ll)
        LinearLayout batteryLl;
        @BindView(R.id.power)
        TextView power;

        public LineHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
