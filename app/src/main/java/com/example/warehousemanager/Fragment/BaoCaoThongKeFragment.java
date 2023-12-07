package com.example.warehousemanager.Fragment;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.warehousemanager.Adapter.Top10Adapter;
import com.example.warehousemanager.Database.WaveHouseDB;
import com.example.warehousemanager.Model.ThongKe;
import com.example.warehousemanager.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;


public class BaoCaoThongKeFragment extends Fragment {


    TextView tvTuNgay,  tvDenNgay , tvSoLuongPhieuNhap , tvTongTienNhap,
            tvSoLuongPhieuXuat, tvTongTienXuat ,
            tvTongDoanhThu, tvTongSoLuongTon , tvTongTienTonKho , tvTongTienHoaDon,
            tvTongPhieu;
    ImageView img_show_hide_tongDoanhThu , imgThongKe;
    RecyclerView rc_top10;
    BarChart barChart;

    //get top10
    List<ThongKe> list;
    Top10Adapter adapter;

    int soLuongPhieuNhap  , soLuongPhieuXuat , tongTienPhieuNhap , tongTienPhieuXuat , soLuongTon , tongTienTonKho, tongTienPhieu , tongSoLuongPhieu ,
    doanhThuPhieuNhap , doanhThuPhieuXuat , tongDoanhThu;
    Calendar calendar = Calendar.getInstance();
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
    int mYear,mMonth,mDay;
    boolean isShow = true;
    private List<String> xValues = Arrays.asList("Phiếu Nhập","Phiếu Xuất");
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_bao_cao_thong_ke, container, false);
        init(view);
        getTop10();
        actionTop();
        actionCenter();
        actionBottom();
        return view;
    }

    public void init(View view){
        tvTuNgay = view.findViewById(R.id.bctk_tvNgayDau);
        tvDenNgay = view.findViewById(R.id.bctk_tvNgayKetThuc);
        tvSoLuongPhieuNhap = view.findViewById(R.id.bctk_tvSoLuongPhieuNhap);
        tvSoLuongPhieuXuat = view.findViewById(R.id.bctk_tvSoLuongPhieuXuat);
        tvTongTienNhap = view.findViewById(R.id.bctk_tvTongTienNhap);
        tvTongTienXuat = view.findViewById(R.id.bctk_tvTongTienXuat);
        tvTongDoanhThu = view.findViewById(R.id.bctk_tvTongDoanhThu);
        tvTongSoLuongTon = view.findViewById(R.id.bctk_tvTongSoLuongTon);
        tvTongTienTonKho = view.findViewById(R.id.bctk_tvTongTienTonKho);
        tvTongTienHoaDon = view.findViewById(R.id.bctk_tvTongTienHoaDon);
        tvTongPhieu = view.findViewById(R.id.bctk_tvTongPhieu);
        img_show_hide_tongDoanhThu = view.findViewById(R.id.bctk_show_hide_tongDoanhThu);
        rc_top10 = view.findViewById(R.id.bctk_rcTop10SanPham);
        barChart = view.findViewById(R.id.thongke_bieuDoDoanhThu);
        imgThongKe = view.findViewById(R.id.imgThongKe);
    }

    public void getTop10(){
        setLayoutAnimation(R.anim.layout_anim);
        list = WaveHouseDB.getInstance(getContext()).dao().getTop10SanPham();
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        rc_top10.setLayoutManager(manager);
        adapter = new Top10Adapter(getContext(), list);
        rc_top10.setAdapter(adapter);
    }
    private void setLayoutAnimation(int animResource){
        LayoutAnimationController layoutAnimationController = AnimationUtils.loadLayoutAnimation(getContext(),animResource);
        rc_top10.setLayoutAnimation(layoutAnimationController);
    }
    public void actionTop(){
        soLuongPhieuNhap = WaveHouseDB.getInstance(getContext()).dao().getCountPhieuNhap();
        tvSoLuongPhieuNhap.setText("Số phiếu nhập: "+soLuongPhieuNhap);

        tongTienPhieuNhap = WaveHouseDB.getInstance(getContext()).dao().getTongTienPhieuNhap();
        tvTongTienNhap.setText(""+tongTienPhieuNhap);

        soLuongPhieuXuat = WaveHouseDB.getInstance(getContext()).dao().getCountPhieuXuat();
        tvSoLuongPhieuXuat.setText("Số phiếu xuất: " + soLuongPhieuXuat + " - ");

        tongTienPhieuXuat = WaveHouseDB.getInstance(getContext()).dao().getTongTienPhieuXuat();
        tvTongTienXuat.setText(" "+tongTienPhieuXuat +" đ");

        tongDoanhThu = tongTienPhieuXuat - tongTienPhieuNhap;
        tvTongDoanhThu.setText(""+tongDoanhThu);

        img_show_hide_tongDoanhThu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isShow){
                    isShow=false;
                    tvTongDoanhThu.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    img_show_hide_tongDoanhThu.setImageResource(R.drawable.eye_closed);

                }else {
                    isShow=true;
                    tvTongDoanhThu.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    img_show_hide_tongDoanhThu.setImageResource(R.drawable.baseline_remove_red_eye_24_hide_password);

                }
            }
        });
    }

    public void actionCenter(){
        soLuongTon = WaveHouseDB.getInstance(getContext()).dao().getSoLuongTon();
        tvTongSoLuongTon.setText(""+soLuongTon);
        //tong tien ton kho

        tongTienTonKho = WaveHouseDB.getInstance(getContext()).dao().getTongTienTonKho() ;
        tvTongTienTonKho.setText(""+tongTienTonKho+" đ");

    }

    public void actionBottom(){
        tongTienPhieu = tongTienPhieuNhap + tongTienPhieuXuat;
        tvTongTienHoaDon.setText(""+tongTienPhieu+" đ");

        tongSoLuongPhieu = soLuongPhieuNhap + soLuongPhieuXuat;
        tvTongPhieu.setText(""+tongSoLuongPhieu + " Phiếu Hóa Đơn");

        DatePickerDialog.OnDateSetListener mDateTuNgay = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                mYear = year;
                mMonth = month;
                mDay = dayOfMonth;
                GregorianCalendar c = new GregorianCalendar(mYear,mMonth,mDay);
                tvTuNgay.setText(sdf.format(c.getTime()));
            }
        };
        DatePickerDialog.OnDateSetListener mDateDenNgay = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                mYear = year;
                mMonth = month;
                mDay = dayOfMonth;
                GregorianCalendar c = new GregorianCalendar(mYear,mMonth,mDay);
                tvDenNgay.setText(sdf.format(c.getTime()));
            }
        };

        tvTuNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                mYear = calendar.get(Calendar.YEAR);
                mMonth = calendar.get(Calendar.MONTH);
                mDay = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog d = new DatePickerDialog(getActivity(),0,mDateTuNgay,mYear,mMonth,mDay);
                d.show();
            }
        });
        tvDenNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                mYear = calendar.get(Calendar.YEAR);
                mMonth = calendar.get(Calendar.MONTH);
                mDay = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog d = new DatePickerDialog(getActivity(),0,mDateDenNgay,mYear,mMonth,mDay);
                d.show();
            }
        });
        tvDenNgay.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String tuNgay = tvTuNgay.getText().toString();
                String denNgay = s.toString();
                if (tuNgay.isEmpty() || denNgay.isEmpty()){
                    Toast.makeText(getContext(), "Yêu cầu chọn ngày", Toast.LENGTH_SHORT).show();
                    return;
                }
                doanhThuPhieuXuat = WaveHouseDB.getInstance(getContext()).dao().getDoanhThuPhieuXuat(tuNgay,denNgay);
                doanhThuPhieuNhap = WaveHouseDB.getInstance(getContext()).dao().getDoanhThuPhieuNhap(tuNgay,denNgay);

                barChart.getAxisRight().setDrawLabels(false);

                ArrayList<BarEntry> entries = new ArrayList<>();
                entries.add(new BarEntry(0,doanhThuPhieuNhap));
                entries.add(new BarEntry(1,doanhThuPhieuXuat));

                YAxis yAxis = barChart.getAxisLeft();
                yAxis.setAxisMinimum(0f);
                yAxis.setAxisMaximum(0f);
                yAxis.setAxisMaximum(doanhThuPhieuNhap+doanhThuPhieuXuat);
                yAxis.setAxisLineWidth(1f);
                yAxis.setAxisLineColor(Color.BLACK);
                yAxis.setLabelCount(10);

                BarDataSet dataSet = new BarDataSet(entries,"Subjects");
                dataSet.setColors(ColorTemplate.MATERIAL_COLORS);

                BarData barData = new BarData(dataSet);
                barChart.setData(barData);

                barChart.getDescription().setEnabled(false);
                barChart.invalidate();

                barChart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(xValues));
                barChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
                barChart.getXAxis().setGranularity(1f);
                barChart.getXAxis().setGranularityEnabled(true);
            }
        });
    }
}