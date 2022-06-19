package com.petterp.latte_ec.main.analysis;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewStub;
import android.widget.ScrollView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rxretifoit.ui.LatteLoader;
import com.fondesa.recyclerviewdivider.RecyclerViewDivider;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.petterp.latte_core.mvp.base.BaseFragment;
import com.petterp.latte_core.mvp.factory.CreatePresenter;
import com.petterp.latte_core.util.color.ColorsUtils;
import com.petterp.latte_ec.R;
import com.petterp.latte_ec.R2;
import com.petterp.latte_ec.main.analysis.dia.DateDialogFragment;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.OnClick;

@CreatePresenter(DataAnalysisPresenter.class)
public class BudgetDelegate extends BaseFragment<DataAnalysisPresenter> implements IDataAnalysisView {
    @BindView(R2.id.tv_title_date)
    AppCompatTextView tvDate = null;
    @BindView(R2.id.tv_analysis_income_money)
    AppCompatTextView tvIncome = null;
    @BindView(R2.id.tv_analysis_balance_money)
    AppCompatTextView tvBalance = null;
    @BindView(R2.id.tv_analysis_consume_money)
    AppCompatTextView tvConsume = null;
    @BindView(R2.id.tv_analysis_day_money)
    AppCompatTextView tvDayAverage = null;
    @BindView(R2.id.lc_analysis_chart)
    LineChart chart = null;
    @BindView(R2.id.tv_select_consume)
    AppCompatTextView tvSelectConsume = null;
    @BindView(R2.id.tv_select_income)
    AppCompatTextView tvSelectIncome = null;

    @BindView(R2.id.scr_stub_analysis)
    ViewStub viewStub = null;
    @BindView(R2.id.scr_analysis_data)
    ScrollView scrollView = null;
    private DataAnalysisClassifyAdapter analysisAdapter;
    private DataAnalysisBillAdapter billAdapter;
    private View stubView;


    private List<List<Entry>> lists;
    private List<String> times;
    private final int MONEY_CONSUME = 1;
    private final int MONEY_INCOME = 2;
    private final int MONEY_INCONSUME = 3;
    private int mode = MONEY_CONSUME;
    private XAxis xAxisInConsume;
    private DecimalFormat decimalFormat = new DecimalFormat("##.##%");
    private DecimalFormat decimalFormat2 = new DecimalFormat("###################.##");

    @OnClick(R2.id.tv_title_date)
    void updateDate() {
        new DateDialogFragment().show(getFragmentManager());
    }

    @BindView(R2.id.bar_data_analysis)
    Toolbar toolbar = null;

    @Override
    public Object setLayout() {
        return R.layout.delegate_budget;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        infoInConsume();
        infoDayBill();
    }

    @Override
    public View setToolbar() {
        return toolbar;
    }


    @Override
    public void updateData(String date) {
        tvDate.setText(date);
    }

    @Override
    public void showLoader() {
        LatteLoader.showLoading(getContext());
    }

    @Override
    public void setInConsume(HashMap<AnalyInConsumeFields, String> map) {
        tvConsume.setText(map.get(AnalyInConsumeFields.CONSUME));
        tvDayAverage.setText(map.get(AnalyInConsumeFields.AVERAGE));
        tvBalance.setText(map.get(AnalyInConsumeFields.BALANCE));
        tvIncome.setText(map.get(AnalyInConsumeFields.INCOME));
    }

    @Override
    public void setDayInConsume(List<List<Entry>> lists, List<String> times) {
        this.lists = lists;
        this.times = times;
        setInConsume();
    }

    /**
     * 初始化每天收支
     */
    private void infoInConsume() {
        xAxisInConsume = chart.getXAxis();
        xAxisInConsume.setDrawAxisLine(true);
        xAxisInConsume.setDrawGridLines(false);
        xAxisInConsume.setAxisMinimum(0);
        xAxisInConsume.setTextSize(13f);
        xAxisInConsume.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxisInConsume.setAxisLineColor(Color.parseColor("#93A8B1"));
        xAxisInConsume.setTextColor(Color.parseColor("#566974"));
        YAxis left = chart.getAxisLeft();
        left.setAxisMinimum(0f);
        chart.getAxisRight().setEnabled(false);
        left.setDrawGridLines(false);
        left.setAxisLineColor(Color.parseColor("#93A8B1"));
        left.setTextColor(Color.parseColor("#566974"));
        chart.getLegend().setEnabled(false);
        chart.getDescription().setEnabled(false);
        chart.setExtraBottomOffset(5);
        chart.setTouchEnabled(false);
    }

    /**
     * 设置
     */
    private void setInConsume() {
        LineDataSet set1 = null;
        LineDataSet set2 = null;
        int size = 0;
        switch (mode) {
            case MONEY_CONSUME:
                size = lists.get(0).size();
                set1 = new LineDataSet(lists.get(0), "");
                set2 = new LineDataSet(null, "");
                break;
            case MONEY_INCOME:
                size = lists.get(1).size();
                set1 = new LineDataSet(null, "");
                set2 = new LineDataSet(lists.get(1), "");
                break;
            case MONEY_INCONSUME:
                size = lists.get(0).size() > lists.get(1).size() ? lists.get(0).size() : lists.get(1).size();
                set1 = new LineDataSet(lists.get(0), "");
                set2 = new LineDataSet(lists.get(1), "");
                break;
            default:
                break;
        }
        xAxisInConsume.setAxisMaximum(size + 1);
        xAxisInConsume.setLabelCount(size + 1);
        int finalSize = size;
        xAxisInConsume.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                if (value == 1 || (value == (finalSize + 1) / 2 && finalSize > 5) || value == finalSize) {
                    return times.get((int) (value));
                }
                return "";
            }
        });
        set1.setDrawCircleHole(false);
        set1.setCircleColor(Color.RED);
        set1.setCircleRadius(2f);
        set1.setColor(Color.RED);
        set1.setDrawValues(false);
        set1.setDrawFilled(true);
        set1.setFillColor(Color.RED);
        set1.setFillAlpha(50);

        set2.setDrawCircleHole(false);
        set2.setCircleColor(Color.GREEN);
        set2.setCircleRadius(2f);
        set2.setColor(Color.GREEN);
        set2.setDrawValues(false);
        set2.setDrawFilled(true);
        set2.setFillColor(Color.GREEN);
        set2.setFillAlpha(50);
        LineData data = new LineData(set1, set2);
        chart.setData(data);
        chart.invalidate();
    }


    private void setMode(int m) {
        if (m != mode) {
            mode = m;
            setInConsume();
        }
    }

    @SuppressLint("Range")


    @Override
    public void setClassifyBill(List<PieEntry> pieList) {
   
    }

    private void infoDayBill() {
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void setDayBill() {
    }

    @Override
    public void setDataMode(boolean mode) {
        if (mode) {
            scrollView.setVisibility(View.VISIBLE);
            if (stubView!=null){
                stubView.setVisibility(View.GONE);
            }
        } else {
            if (stubView == null) {
                stubView = viewStub.inflate();
            }
            stubView.setVisibility(View.VISIBLE);
            scrollView.setVisibility(View.GONE);
        }
    }
}
