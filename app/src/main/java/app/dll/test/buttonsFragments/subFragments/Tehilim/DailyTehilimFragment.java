package app.dll.test.buttonsFragments.subFragments.Tehilim;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.github.barteksc.pdfviewer.PDFView;

import java.io.IOException;

import app.dll.test.R;
import app.dll.test.specialUtils.SpecialUtils;

public class DailyTehilimFragment extends Fragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_daily_tehilim, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        PDFView pdfView = view.findViewById(R.id.pdfView);
        if (pdfView != null) {
            try {
                pdfView.fromAsset("Tehillim.pdf")
                        .defaultPage(0)
                        .enableSwipe(true)
                        .swipeHorizontal(false)
                        .enableDoubletap(true)
                        .pageFitPolicy(com.github.barteksc.pdfviewer.util.FitPolicy.WIDTH)
                        .spacing(10)
                        .load();
                Toast.makeText(requireContext(), "PDF loaded successfully", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                Log.e("TehilimFragment", "Exception during PDF load", e);
                Toast.makeText(requireContext(), "PDF loading failed: " + e.getMessage(), Toast.LENGTH_LONG).show();
            }
        } else {
            Log.e("TehilimFragment", "PDFView not found in layout");
            Toast.makeText(requireContext(), "PDF view not found", Toast.LENGTH_SHORT).show();
        }



    }
}