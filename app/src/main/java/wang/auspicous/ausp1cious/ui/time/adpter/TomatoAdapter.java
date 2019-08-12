package wang.auspicous.ausp1cious.ui.time.adpter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.orhanobut.logger.Logger;

import wang.auspicous.ausp1cious.R;
import wang.auspicous.ausp1cious.widgets.TomatoTimeView;

public class TomatoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;

    public TomatoAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TomatoAdapterHolder(LayoutInflater.from(context).inflate(R.layout.item_tomato_time,null,false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof TomatoAdapterHolder) {
            Logger.i("显示Bind");
            ((TomatoAdapterHolder) holder).ttvTime.setPeriods(position);
        }
    }

    @Override
    public int getItemCount() {
        return 500;
    }

    private class TomatoAdapterHolder extends RecyclerView.ViewHolder {
        private TomatoTimeView ttvTime;
        public TomatoAdapterHolder(@NonNull View itemView) {
            super(itemView);
            ttvTime = itemView.findViewById(R.id.ttv_time);
        }
    }

}
