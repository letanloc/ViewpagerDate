package efood.loc.com.viewpagerdate.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.text.ParseException;
import java.util.Date;

import efood.loc.com.viewpagerdate.R;
import loc.lbs.PagerDatePickerDateFormat;
import loc.lbs.adapter.DatePagerFragmentAdapter;
import loc.lbs.adapter.DefaultDateAdapter;
import loc.lbs.model.DateItem;
import loc.lbs.view.DateRecyclerView;
import loc.lbs.view.RecyclerViewInsetDecoration;

//import pl.rspective.pagerdatepicker.PagerDatePickerDateFormat;
//import pl.rspective.pagerdatepicker.adapter.DatePagerFragmentAdapter;
//import pl.rspective.pagerdatepicker.adapter.DefaultDateAdapter;
//import pl.rspective.pagerdatepicker.model.DateItem;
//import pl.rspective.pagerdatepicker.sample.R;
//import pl.rspective.pagerdatepicker.view.DateRecyclerView;
//import pl.rspective.pagerdatepicker.view.RecyclerViewInsetDecoration;
//
public class DatePickerDefaultFragment extends Fragment {

    private DateRecyclerView dateList;
    private ViewPager pager;

    public static DatePickerDefaultFragment newInstance() {
        return new DatePickerDefaultFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_picker_default, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        pager = (ViewPager) view.findViewById(R.id.pager);
        dateList = (DateRecyclerView) view.findViewById(R.id.date_list);

        dateList.addItemDecoration(new RecyclerViewInsetDecoration(getActivity(), R.dimen.date_card_insets));

        Date start = null;
        Date end = null;
        Date defaultDate = null;

        try {
            start = PagerDatePickerDateFormat.DATE_PICKER_DD_MM_YYYY_FORMAT.parse("02-12-2000");
            end = PagerDatePickerDateFormat.DATE_PICKER_DD_MM_YYYY_FORMAT.parse("02-12-2020");

            defaultDate = PagerDatePickerDateFormat.DATE_PICKER_DD_MM_YYYY_FORMAT.parse("01-04-2015");

        } catch (ParseException e) {
            e.printStackTrace();
        }

        dateList.setAdapter(new DefaultDateAdapter(start, end, defaultDate));

        DatePagerFragmentAdapter fragmentAdapter = new DatePagerFragmentAdapter(getFragmentManager(), dateList.getDateAdapter()) {
            @Override
            protected Fragment getFragment(int position, long date) {
                return SimplePageFragment.newInstance(position, date);
            }
        };

        pager.setAdapter(fragmentAdapter);
        dateList.setPager(pager);

        dateList.setDatePickerListener(new DateRecyclerView.DatePickerListener() {
            @Override
            public void onDatePickerItemClick(DateItem dateItem, int position) {
                Toast.makeText(getActivity(), "Clicked: " + position, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDatePickerPageSelected(int position) {

            }

            @Override
            public void onDatePickerPageStateChanged(int state) {

            }

            @Override
            public void onDatePickerPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }
        });

    }
}
