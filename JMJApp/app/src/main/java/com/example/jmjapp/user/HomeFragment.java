package com.example.jmjapp.user;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.example.jmjapp.Adv.AdvFragment1;
import com.example.jmjapp.Adv.AdvFragment2;
import com.example.jmjapp.Adv.AdvFragment3;
import com.example.jmjapp.R;

public class HomeFragment extends Fragment {
    int i = 0;

    ViewPager pager;

    AdvFragment1 advFragment1;
    AdvFragment2 advFragment2;
    AdvFragment3 advFragment3;

    ImageButton koreanfood;
    ImageButton japanesefood;
    ImageButton chinesefood;
    ImageButton meat;
    ImageButton bunsick;
    ImageButton soju;
    ImageButton fastfood;
    ImageButton tang;
    ImageButton desssert;

    MapFragment mapFragment;

    FragmentManager manager;

    ViewFlipper viewFlipper;

    public HomeFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.home_fragment2, container, false);

        int images[] = {
                R.drawable.adv1,
                R.drawable.adv2,
                R.drawable.adv3
        };

        viewFlipper = rootView.findViewById(R.id.user_img_slide);

        for (int image : images) {
            slideImage(image);
        }

        mapFragment = new MapFragment();
        manager = getFragmentManager();

        ImageView home_search_bar = rootView.findViewById(R.id.home_search_bar);
        home_search_bar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.bottomNavigation.setSelectedItemId(R.id.tab2);
                manager.beginTransaction().replace(R.id.container, mapFragment).commit();
            }
        });

        ImageView imageButton = rootView.findViewById(R.id.koreanfood);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MenuActivity.class);
                intent.putExtra("menu", 0);
                startActivity(intent);
            }
        });

        ImageView imageButton2 = rootView.findViewById(R.id.japansesfood);
        imageButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MenuActivity.class);
                intent.putExtra("menu", 1);
                startActivity(intent);
            }
        });

        ImageView imageButton3 = rootView.findViewById(R.id.chinesefood);
        imageButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MenuActivity.class);
                intent.putExtra("menu", 2);
                startActivity(intent);
            }
        });

        ImageView imageButton4 = rootView.findViewById(R.id.meat);
        imageButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MenuActivity.class);
                intent.putExtra("menu", 3);
                startActivity(intent);
            }
        });

        ImageView imageButton5 = rootView.findViewById(R.id.bunsick);
        imageButton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MenuActivity.class);
                intent.putExtra("menu", 4);
                startActivity(intent);
            }
        });

        ImageView imageButton6 = rootView.findViewById(R.id.soju);
        imageButton6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MenuActivity.class);
                intent.putExtra("menu", 5);
                startActivity(intent);
            }
        });

        ImageView imageButton7 = rootView.findViewById(R.id.fastfood);
        imageButton7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MenuActivity.class);
                intent.putExtra("menu", 6);
                startActivity(intent);
            }
        });

        ImageView imageButton8 = rootView.findViewById(R.id.tang);
        imageButton8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MenuActivity.class);
                intent.putExtra("menu", 7);
                startActivity(intent);
            }
        });

        ImageView imageButton9 = rootView.findViewById(R.id.dessert);
        imageButton9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MenuActivity.class);
                intent.putExtra("menu", 8);
                startActivity(intent);
            }
        });

        Button button = rootView.findViewById(R.id.bell_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), BellActivity.class);
                startActivity(intent);
            }
        });

        Button button1 = rootView.findViewById(R.id.qr_button);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), QrReaderActivity.class);
                startActivity(intent);
            }
        });

        initUI(rootView);

        return rootView;
    }

    private void slideImage(int image) {
        ImageView imageView = new ImageView(getActivity());
        imageView.setBackgroundResource(image);

        viewFlipper.addView(imageView);
        viewFlipper.setFlipInterval(4000);
        viewFlipper.setAutoStart(true);

        viewFlipper.setInAnimation(getActivity(),android.R.anim.slide_in_left);
        viewFlipper.setOutAnimation(getActivity(),android.R.anim.slide_out_right);
    }

    private void initUI(ViewGroup rootView) {

    }

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onStop() {
        super.onStop();

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onPause() {
        super.onPause();

    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }

    class MyPagerAdapter extends FragmentPagerAdapter {
        public MyPagerAdapter(FragmentManager fm){
            super(fm);
            getItem(0);
        }
        public Fragment getItem(int position){
            if(position==0)
            {
                return advFragment1;
            }else if(position==1){
                return advFragment2;
            }else {
                return advFragment3;
            }
        }
        public int getCount(){
            return 3;
        }

    }

//    private void scanCode() {
//        IntentIntegrator intentIntegrator = new IntentIntegrator(getActivity());
//        intentIntegrator.setCaptureActivity(QrReaderActivity.class);
//        intentIntegrator.setOrientationLocked(false);
//        intentIntegrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
//        intentIntegrator.setPrompt("Scanning code");
//        intentIntegrator.initiateScan();
//    }
//
//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
//        if(result != null) {
//            if(result.getContents() != null) {
//                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//                builder.setMessage(result.getContents());
//                builder.setTitle("Scanning Result");
//                builder.setPositiveButton("Scan Again", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        scanCode();
//                    }
//                }).setNegativeButton("finish", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//
//                    }
//                });
//            }
//        }
//    }
}
