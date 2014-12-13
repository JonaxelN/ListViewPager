package viewpager;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.jonaxel.listviewpager.R;

/**
 * Created by jonathan on 29/10/14.
 */
public class Fragment3 extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (container == null) {
            return null;
        }

        return (LinearLayout) inflater.inflate(R.layout.viewpager_fragment3, container, false);
    }
}
