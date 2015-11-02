package jp.ac.wiz.toolbarsample;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //タブとして使用するViewを管理するアダプター
    private SectionsPagerAdapter mSectionsPagerAdapter;

    //横スライドで画面を切り替えるクラス
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //android5.0以降からActionBarクラスを使用するのは非推奨
        //その代わりにToolbarを使用する
        //Toolbarはビュー毎で管理するようななりました。
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //ToolbarをActionBarとして登録
        setSupportActionBar(toolbar);

        //タブ表示するアダプターのインスタンス生成
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());


        //activity_main.xmlのViewPagerと紐付け
        mViewPager = (ViewPager) findViewById(R.id.container);
        //ViewPagerにアダプター登録
        mViewPager.setAdapter(mSectionsPagerAdapter);

        //activity_main.xmlのTabLayoutと紐付け
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        //TabとViewの関連付け
        tabLayout.setupWithViewPager(mViewPager);




    }

    //メニューを作成する時に呼ばれるメソッド
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    //メニューの項目を選択された時に呼ばれるメソッド
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();


        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    //タブ表示するアダプタークラス作成
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        //コンストラクタでフラグメントを管理しているインスタンス取得
        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        //各画面のフラグメント作成するメソッド
        @Override
        public Fragment getItem(int position) {

            return PlaceholderFragment.newInstance(position + 1);
        }

        //各画面のフラグメントの数を返すメソッド
        @Override
        public int getCount() {

            return 3;
        }

        //タブに表示するタイトルを返すメソッド
        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "SECTION 1";
                case 1:
                    return "SECTION 2";
                case 2:
                    return "SECTION 3";
            }
            return null;
        }
    }


    //各画面のフラグメントを作成するクラス
    public static class PlaceholderFragment extends Fragment {

        //フラグメント内で使用する共通のキー文字列作成
        private static final String ARG_SECTION_NUMBER = "section_number";


        //フラグメントのインスタンス作成メソッド
        public static PlaceholderFragment newInstance(int sectionNumber) {
            //フラグメントのインスタンス生成
            PlaceholderFragment fragment = new PlaceholderFragment();
            //データを束ねるように変数を作成
            Bundle args = new Bundle();
            //Map形式でデータを登録
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            //フラグメントに束ねたデータを登録
            //FragmentはActivityとは別にライフサイクルを持っているためこのようにしてデータを渡す
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        //フラグメントの画面を作成する時に呼ばれるライフサイクルメソッド
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            //どのFragmentLayoutを使ってフラグメントのViewを作成するか第1引数で指定
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            //FragmentのTextViewとプログラムを紐付け
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            //TextViewに表示する文字を設定
            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            return rootView;
        }
    }
}
