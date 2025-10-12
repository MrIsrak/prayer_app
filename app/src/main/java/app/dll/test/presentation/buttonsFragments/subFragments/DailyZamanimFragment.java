package app.dll.test.presentation.buttonsFragments.subFragments;

import static app.dll.test.presentation.buttonsFragments.TimetableFragment.dates;
import static app.dll.test.presentation.utils.dateUtils.GetDate.getDayInt;
import static app.dll.test.presentation.utils.dateUtils.GetDate.getToday;
import static app.dll.test.presentation.utils.dateUtils.GetDate.getTodayString;
import static app.dll.test.presentation.userDataPrefs.userLocationData.GetLocation.getLocationName;
import static app.dll.test.presentation.utils.dateUtils.GetPrayersTime.alosHashachar;
import static app.dll.test.presentation.utils.dateUtils.GetPrayersTime.chatzot;
import static app.dll.test.presentation.utils.dateUtils.GetPrayersTime.getPrayersTime;
import static app.dll.test.presentation.utils.dateUtils.GetPrayersTime.minchaGedolah;
import static app.dll.test.presentation.utils.dateUtils.GetPrayersTime.minchaKetanah;
import static app.dll.test.presentation.utils.dateUtils.GetPrayersTime.netzHaChamah;
import static app.dll.test.presentation.utils.dateUtils.GetPrayersTime.plagHaMincha;
import static app.dll.test.presentation.utils.dateUtils.GetPrayersTime.shkiah;
import static app.dll.test.presentation.utils.dateUtils.GetPrayersTime.sofZmanKriatShema;
import static app.dll.test.presentation.utils.dateUtils.GetPrayersTime.sofZmanTefillah;
import static app.dll.test.presentation.utils.dateUtils.GetPrayersTime.tzeitHaKochavim;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.gms.location.FusedLocationProviderClient;

import java.text.ParseException;
import java.util.Calendar;

import app.dll.test.R;
import app.dll.test.presentation.utils.dateUtils.GetDate;
import app.dll.test.presentation.userDataPrefs.userLocationData.GetLocation;
import app.dll.test.presentation.utils.dateUtils.GetPrayersTime;

public class DailyZamanimFragment extends Fragment {
    private TextView tvLocation, tvAlosHashachar, tvNetzHachamah, tvSofZmanKriatShema;
    private TextView tvSofZmanTefillah, tvChatzot, tvMinchaGedolah, tvMinchaKetanah;
    private TextView tvPlagHamincha, tvShkiah, tvTzeitHakochavim;

    private FusedLocationProviderClient fusedLocationClient;

    public static String day = "";
    public DailyZamanimFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_daly_zamanim, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
        GetLocation.getLastLocation(requireActivity());
        getPrayersTime(requireActivity());

        //Zmanim fields
        tvLocation = view.findViewById(R.id.tv_location);
        tvAlosHashachar = view.findViewById(R.id.tv_alos_hashachar);
        tvNetzHachamah = view.findViewById(R.id.tv_netz_hachamah);
        tvSofZmanKriatShema = view.findViewById(R.id.tv_sof_zman_kriat_shema);
        tvSofZmanTefillah = view.findViewById(R.id.tv_sof_zman_tefillah);
        tvChatzot = view.findViewById(R.id.tv_chatzot);
        tvMinchaGedolah = view.findViewById(R.id.tv_mincha_gedolah);
        tvMinchaKetanah = view.findViewById(R.id.tv_mincha_ketanah);
        tvPlagHamincha = view.findViewById(R.id.tv_plag_hamincha);
        tvShkiah = view.findViewById(R.id.tv_shkiah);
        tvTzeitHakochavim = view.findViewById(R.id.tv_tzeit_hakochavim);

        setZmanim();

        //////////////////////////////////////


        ImageButton back_button = view.findViewById(R.id.back_button);
        back_button.setOnClickListener(v -> {
            // Navigate back to main_menu_button
            navController.navigate(R.id.action_dailyZmanim_to_timetable);
        });
        day = getArguments().getString("dayOfWeek");
        checkDay(view);
        setDay(view, day);
        setDate(view);

        try {
            GetPrayersTime.getPrayersTime(requireActivity(), getDate(view));
            setZmanim();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }


        ImageButton day_back_button = view.findViewById(R.id.day_back_btn);
        ImageButton day_frvrd_button = view.findViewById(R.id.day_fvrd_btn);

        day_back_button.setOnClickListener(v -> {
            setDay(view, GetDate.daySwitch(getDay(view),requireContext(), false));
            setDate(view);
            try {
                GetPrayersTime.getPrayersTime(requireActivity(), getDate(view));
                setZmanim();
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        });
        day_frvrd_button.setOnClickListener(v -> {
            setDay(view, GetDate.daySwitch(getDay(view), requireContext(), true));
            setDate(view);
            try {
                GetPrayersTime.getPrayersTime(requireActivity(), getDate(view));
                setZmanim();
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        });
//        Log.d("DATES", dates.toString());
    }

    private void setZmanim() {
        tvLocation.setText(getString(R.string.location)+ " " + getLocationName(requireActivity()));
        tvAlosHashachar.setText(getString(R.string.alot_hashachar) + " " + alosHashachar);
        tvNetzHachamah.setText(getString(R.string.netz_hachamah) + " " + netzHaChamah);
        tvSofZmanKriatShema.setText(getString(R.string.sof_zman_kriat_shema) + " " + sofZmanKriatShema);
        tvSofZmanTefillah.setText(getString(R.string.sof_zman_tefillah) + " " + sofZmanTefillah);
        tvChatzot.setText(getString(R.string.chatzot) + " " + chatzot);
        tvMinchaGedolah.setText(getString(R.string.mincha_gedolah) + " " + minchaGedolah);
        tvMinchaKetanah.setText(getString(R.string.mincha_ketanah) + " " + minchaKetanah);
        tvPlagHamincha.setText(getString(R.string.plag_hamincha) + " " + plagHaMincha);
        tvShkiah.setText(getString(R.string.shkiah) + " " + shkiah);
        tvTzeitHakochavim.setText(getString(R.string.tzeit_hakochavim) + " " + tzeitHaKochavim);
    }

    private void checkDay(View view){
        TextView dayTextView = view.findViewById(R.id.week_day_tv);
        if(day.equals(getToday())){dayTextView.setText(getTodayString(getContext()));}
        dayTextView.setText(day);
    }
    private void setDay(View view, String text){
        TextView dayTextView = view.findViewById(R.id.week_day_tv);
        dayTextView.setText(text);
    }
    private void setDate(View view){
        TextView dateTextView = view.findViewById(R.id.date_tv);
        day = getDay(view);
        dateTextView.setText(dates.get(getDayInt(day, requireContext())-1));
    }
    private String getDate(View view){
        TextView dateTextView = view.findViewById(R.id.date_tv);
        return (String) dateTextView.getText();
    }
    private String getDay(View view){
        TextView dayTextView = view.findViewById(R.id.week_day_tv);
        return (String) dayTextView.getText();
    }
}