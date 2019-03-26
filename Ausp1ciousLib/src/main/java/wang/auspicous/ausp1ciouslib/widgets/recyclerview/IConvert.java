package wang.auspicous.ausp1ciouslib.widgets.recyclerview;

import java.util.List;

/**
 * Created by Ausp1cious on 2019/3/20.
 */
public interface IConvert<T> {
    void convert(BaseViewHolder viewHolder, T itemData);
}
