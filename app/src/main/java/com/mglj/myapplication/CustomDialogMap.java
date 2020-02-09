package com.mglj.myapplication;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;

public class CustomDialogMap extends Activity{

    private Context context;
    Intent intent;
    String url_name="x";
    String tel;
    boolean call_status, open_status;
    public CustomDialogMap(Context context) {

        this.context = context;

    }

    // 호출할 다이얼로그 함수를 정의한다.
    public void callFunction(String maker_name, String address1) {

        // 커스텀 다이얼로그를 정의하기위해 Dialog클래스를 생성한다.
        final Dialog dlg = new Dialog(context);

        // 액티비티의 타이틀바를 숨긴다.
        dlg.requestWindowFeature(Window.FEATURE_NO_TITLE);

        // 커스텀 다이얼로그의 레이아웃을 설정한다.
        dlg.setContentView(R.layout.custom_dialog_map);

        // 커스텀 다이얼로그를 노출한다.
        dlg.show();

        // 커스텀 다이얼로그의 각 위젯들을 정의한다.
        final TextView message = (TextView) dlg.findViewById(R.id.mesgase);

        final ImageView map_img = (ImageView)dlg.findViewById(R.id.map_img);


        final Button cancelButton = (Button) dlg.findViewById(R.id.cancelButton);

        final TextView text_map_flag = (TextView)dlg.findViewById(R.id.text_map_flag);
        final TextView text_map_open = (TextView)dlg.findViewById(R.id.text_map_open);
        final TextView text_map_call = (TextView)dlg.findViewById(R.id.text_map_call);
        final TextView text_map_time = (TextView)dlg.findViewById(R.id.text_map_time);
        final TextView text_map_money = (TextView)dlg.findViewById(R.id.text_map_money);

        message.setText(maker_name);

        if(maker_name.equals("상의자동차야영장")){
            Glide.with(context).load(R.drawable.mainimg).fitCenter().into(map_img);
            text_map_flag.setText("경상북도 청송군 부동면 상의리 406");
            text_map_call.setText("054-870-5341");
            text_map_open.setText("reservation.knps.or.kr");
            text_map_time.setText("정보가 없습니다");
            open_status = true;
            call_status = true;
        }
        //애견용품
        else if(maker_name.equals("굿애견용품할인매장")){
            Glide.with(context).load(R.drawable.toy).fitCenter().into(map_img);
            text_map_flag.setText("경기도 고양시 일산서구 탄현동 고봉로 419");
            text_map_call.setText("031-914-4079");
            text_map_open.setText("정보가 없습니다");
            text_map_time.setText("오전 10:00~오후 8:00");
            call_status = true;
        }
        else if(maker_name.equals("퍼피하우스")){
            Glide.with(context).load(R.drawable.toy).fitCenter().into(map_img);
            text_map_flag.setText("서울특별시 강북구 수유동 39-3");
            text_map_call.setText("02-902-4777");
            text_map_open.setText("정보가 없습니다");
            text_map_time.setText("정보가 없습니다");
            call_status = true;
        }
        else if(maker_name.equals("(주)펫그라운드 애견용품 아울렛")){
            Glide.with(context).load(R.drawable.toy).fitCenter().into(map_img);
            text_map_flag.setText("서울특별시 도봉구 창동 2층");
            text_map_call.setText("02-400-6521");
            text_map_open.setText("https://petground.business.site/?utm_source=gmb&utm_medium=referral");
            text_map_time.setText("오후 1:00~9:00(일요일 휴무)");
            call_status = true;
            open_status = true;
        }
        else if(maker_name.equals("좋은나라애견본부")){
            Glide.with(context).load(R.drawable.toy).fitCenter().into(map_img);
            text_map_flag.setText("서울특별시 성북구 장위동 173-119");
            text_map_call.setText("02-915-6677");
            text_map_open.setText("http://www.joeunpet.com/src/main/indexpage.php");
            text_map_time.setText("정보가 없습니다");
            call_status = true;
            open_status = true;
        }
        else if(maker_name.equals("아마존애견용품할인마트")){
            Glide.with(context).load(R.drawable.toy).fitCenter().into(map_img);
            text_map_flag.setText("경기도 구리시 교문동 270-2");
            text_map_call.setText("031-564-0073");
            text_map_open.setText("정보가 없습니다");
            text_map_time.setText("정보가 없습니다");
            call_status = true;
        }
        else if(maker_name.equals("애견마트")){
            Glide.with(context).load(R.drawable.toy).fitCenter().into(map_img);
            text_map_flag.setText("경기도 구리시 토평동 617-3");
            text_map_call.setText("031-567-7788");
            text_map_open.setText("정보가 없습니다");
            text_map_time.setText("오전 9:00~오후 10:00");
            call_status = true;
        }
        else if(maker_name.equals("갤럭시펫 petshop")){
            Glide.with(context).load(R.drawable.toy).fitCenter().into(map_img);
            text_map_flag.setText("서울특별시 강남구 삼성1동 봉은사로104길 10");
            text_map_call.setText("02-6951-1120");
            text_map_open.setText("http://www.galaxypetmall.com/");
            text_map_time.setText("오전 10:00~오후 9:00");
            call_status = true;
            open_status = true;
        }
        else if(maker_name.equals("펫마트 양천점")){
            Glide.with(context).load(R.drawable.toy).fitCenter().into(map_img);
            text_map_flag.setText("신월동 1001-2번지 1층 양천구 서울특별시 KR");
            text_map_call.setText("02-2604-1550");
            text_map_open.setText("정보가 없습니다");
            text_map_time.setText("오전 9:30~오후 10:30");
            call_status = true;
            open_status = true;
        }
        else if(maker_name.equals("스토어봄 부천지사")){
            Glide.with(context).load(R.drawable.toy).fitCenter().into(map_img);
            text_map_flag.setText("경기도 부천시 춘의동 옥산로 136");
            text_map_call.setText("070-4870-4477");
            text_map_open.setText("https://store.bom.co.kr/");
            text_map_time.setText("오전 10:00~오후 11:00");
            call_status = true;
            open_status = true;
        }
        else if(maker_name.equals("하비러브 애견용품")){
            Glide.with(context).load(R.drawable.toy).fitCenter().into(map_img);
            text_map_flag.setText("인천광역시 남구 주안6동 920-8");
            text_map_call.setText("032-441-9552");
            text_map_open.setText("http://www.maumpet.net/");
            text_map_time.setText("정보가 없습니다");
            call_status = true;
            open_status = true;
        }
        else if(maker_name.equals("이지애견용품")){
            Glide.with(context).load(R.drawable.toy).fitCenter().into(map_img);
            text_map_flag.setText("경기도 시흥시 대야동 562-5");
            text_map_call.setText("031-314-7976");
            text_map_open.setText("정보가 없습니다");
            text_map_time.setText("정보가 없습니다");
            call_status = true;
        }
        else if(maker_name.equals("애견용품 창고형 할인매장")){
            Glide.with(context).load(R.drawable.toy).fitCenter().into(map_img);
            text_map_flag.setText("경기도 시흥시 정왕본동 2320-2");
            text_map_call.setText("031-520-0289");
            text_map_open.setText("정보가 없습니다");
            text_map_time.setText("오전 10:00~오후 10:00");
            call_status = true;
        }
        else if(maker_name.equals("산본팻애견용품할인마트")){
            Glide.with(context).load(R.drawable.toy).fitCenter().into(map_img);
            text_map_flag.setText("경기도 군포시 산본1동 79-69");
            text_map_call.setText("031-393-0033");
            text_map_open.setText("정보가 없습니다");
            text_map_time.setText("정보가 없습니다");
            call_status = true;
        }
        else if(maker_name.equals("안양펫애견용품할인마트")){
            Glide.with(context).load(R.drawable.toy).fitCenter().into(map_img);
            text_map_flag.setText("경기도 안양시 동안구 호계동 934-26");
            text_map_call.setText("031-381-0011");
            text_map_open.setText("정보가 없습니다");
            text_map_time.setText("정보가 없습니다");
            call_status = true;
        }
        else if(maker_name.equals("관양펫 애견용품할인마트")){
            Glide.with(context).load(R.drawable.toy).fitCenter().into(map_img);
            text_map_flag.setText("경기도 안양시 동안구 관악대로 400");
            text_map_call.setText("031-422-0202");
            text_map_open.setText("정보가 없습니다");
            text_map_time.setText("오전 10:00~오후 9:00");
            call_status = true;
        }
        else if(maker_name.equals("루루애견용품할인매장")){
            Glide.with(context).load(R.drawable.toy).fitCenter().into(map_img);
            text_map_flag.setText("경기도 용인시 기흥구 신갈동 70-6");
            text_map_call.setText("031-283-0702");
            text_map_open.setText("정보가 없습니다");
            text_map_time.setText("정보가 없습니다");
            call_status = true;
        }
        else if(maker_name.equals("가람펫애견용품할인점")){
            Glide.with(context).load(R.drawable.toy).fitCenter().into(map_img);
            text_map_flag.setText("경기도 광주시 송정동 58-9");
            text_map_call.setText("031-763-0079");
            text_map_open.setText("정보가 없습니다");
            text_map_time.setText("정보가 없습니다");
            call_status = true;
        }
        else if(maker_name.equals("애견하우스")){
            Glide.with(context).load(R.drawable.toy).fitCenter().into(map_img);
            text_map_flag.setText("경기도 평택시 안중읍 안중리 266-13");
            text_map_call.setText("031-684-2829");
            text_map_open.setText("정보가 없습니다");
            text_map_time.setText("정보가 없습니다");
            call_status = true;
        }
        else if(maker_name.equals("로또애견용품할인매장")){
            Glide.with(context).load(R.drawable.toy).fitCenter().into(map_img);
            text_map_flag.setText("경기도 평택시 세교동");
            text_map_call.setText("031-656-7811");
            text_map_open.setText("정보가 없습니다");
            text_map_time.setText("정보가 없습니다");
            call_status = true;
        }
        else if(maker_name.equals("코리아애견용품전문점")){
            Glide.with(context).load(R.drawable.toy).fitCenter().into(map_img);
            text_map_flag.setText("장안동 27-1번지 평택시 경기도 KR");
            text_map_call.setText("031-666-0030");
            text_map_open.setText("정보가 없습니다");
            text_map_time.setText("정보가 없습니다");
            call_status = true;
        }
        else if(maker_name.equals("장보는강아지와고양이용인포곡점")){
            Glide.with(context).load(R.drawable.toy).fitCenter().into(map_img);
            text_map_flag.setText("경기도 용인시 처인구 영문리 113-6 KR");
            text_map_call.setText("031-666-0030");
            text_map_open.setText("http://www.makeus.net/32066");
            text_map_time.setText("오전 10:00~오후 9:30");
            call_status = true;
        }
        else if(maker_name.equals("강아지나라")){
            Glide.with(context).load(R.drawable.toy).fitCenter().into(map_img);
            text_map_flag.setText("경기도 이천시 창전동 87-8");
            text_map_call.setText("031-637-8482");
            text_map_open.setText("정보가 없습니다");
            text_map_time.setText("정보가 없습니다");
            call_status = true;
        }
        else if(maker_name.equals("아이펫2000")){
            Glide.with(context).load(R.drawable.toy).fitCenter().into(map_img);
            text_map_flag.setText("경기도 이천시 진리동 경충대로2481번길 7");
            text_map_call.setText("031-638-4520");
            text_map_open.setText("http://www.ipet2000.com/");
            text_map_time.setText("정보가 없습니다");
            call_status = true;
            open_status = true;
        }
        else if(maker_name.equals("애견나라")){
            Glide.with(context).load(R.drawable.toy).fitCenter().into(map_img);
            text_map_flag.setText("경기도 여주군 여주읍 상리 283-7");
            text_map_call.setText("031-881-0035");
            text_map_open.setText("정보가 없습니다");
            text_map_time.setText("정보가 없습니다");
            call_status = true;
        }
        else if(maker_name.equals("해피애견 무극리")){
            Glide.with(context).load(R.drawable.toy).fitCenter().into(map_img);
            text_map_flag.setText("충청북도 음성군 금왕읍 무극리 663-2");
            text_map_call.setText("043-883-4277");
            text_map_open.setText("정보가 없습니다");
            text_map_time.setText("정보가 없습니다");
            call_status = true;
        }
        else if(maker_name.equals("해피애견 평곡리")){
            Glide.with(context).load(R.drawable.toy).fitCenter().into(map_img);
            text_map_flag.setText("충청북도 음성군 음성읍 평곡리 963-17");
            text_map_call.setText("043-873-4276");
            text_map_open.setText("정보가 없습니다");
            text_map_time.setText("정보가 없습니다");
            call_status = true;
        }
        else if(maker_name.equals("나라애견")){
            Glide.with(context).load(R.drawable.toy).fitCenter().into(map_img);
            text_map_flag.setText("충청북도 충주시 지현동 90");
            text_map_call.setText("043-857-0079");
            text_map_open.setText("정보가 없습니다");
            text_map_time.setText("정보가 없습니다");
            call_status = true;
        }
        else if(maker_name.equals("동물나라애견")){
            Glide.with(context).load(R.drawable.toy).fitCenter().into(map_img);
            text_map_flag.setText("충청북도 충주시 교현동 1087-1");
            text_map_call.setText("043-854-0213");
            text_map_open.setText("정보가 없습니다");
            text_map_time.setText("정보가 없습니다");
            call_status = true;
        }
        else if(maker_name.equals("퍼피클럽")){
            Glide.with(context).load(R.drawable.toy).fitCenter().into(map_img);
            text_map_flag.setText("충청북도 제천시 화산동 205-1");
            text_map_call.setText("043-646-1598");
            text_map_open.setText("정보가 없습니다");
            text_map_time.setText("정보가 없습니다");
            call_status = true;
        }
        else if(maker_name.equals("토토애견")){
            Glide.with(context).load(R.drawable.toy).fitCenter().into(map_img);
            text_map_flag.setText("충청북도 제천시 중앙로1가 116-9");
            text_map_call.setText("043-647-2630");
            text_map_open.setText("정보가 없습니다");
            text_map_time.setText("정보가 없습니다");
            call_status = true;
        }
        else if(maker_name.equals("러브애견")){
            Glide.with(context).load(R.drawable.toy).fitCenter().into(map_img);
            text_map_flag.setText("충청북도 제천시 중앙로2가 22-11");
            text_map_call.setText("043-648-7124");
            text_map_open.setText("정보가 없습니다");
            text_map_time.setText("정보가 없습니다");
            call_status = true;
        }
        else if(maker_name.equals("아라애견")){
            Glide.with(context).load(R.drawable.toy).fitCenter().into(map_img);
            text_map_flag.setText("경상북도 영주시 영주2동 505-7");
            text_map_call.setText("054-636-6346");
            text_map_open.setText("정보가 없습니다");
            text_map_time.setText("정보가 없습니다");
            call_status = true;
        }
        else if(maker_name.equals("복이네애견미용실")){
            Glide.with(context).load(R.drawable.toy).fitCenter().into(map_img);
            text_map_flag.setText("경상북도 영주시 영주2동 534-19");
            text_map_call.setText("054-633-0094");
            text_map_open.setText("정보가 없습니다");
            text_map_time.setText("정보가 없습니다");
            call_status = true;
        }
        else if(maker_name.equals("애견용품전문매장")){
            Glide.with(context).load(R.drawable.toy).fitCenter().into(map_img);
            text_map_flag.setText("경상북도 영주시 가흥1동 1454-19");
            text_map_call.setText("054-636-8881");
            text_map_open.setText("정보가 없습니다");
            text_map_time.setText("정보가 없습니다");
            call_status = true;
        }
        else if(maker_name.equals("영심이애견샵")){
            Glide.with(context).load(R.drawable.toy).fitCenter().into(map_img);
            text_map_flag.setText("경상북도 안동시 태화동");
            text_map_call.setText("054-859-7687");
            text_map_open.setText("정보가 없습니다");
            text_map_time.setText("정보가 없습니다");
            call_status = true;
        }
        else if(maker_name.equals("밍키애견마트")){
            Glide.with(context).load(R.drawable.toy).fitCenter().into(map_img);
            text_map_flag.setText("경상북도 안동시 옥야동 91-12");
            text_map_call.setText("054-854-5977");
            text_map_open.setText("정보가 없습니다");
            text_map_time.setText("정보가 없습니다");
            call_status = true;
        }
        else if(maker_name.equals("스마일퍼피")){
            Glide.with(context).load(R.drawable.toy).fitCenter().into(map_img);
            text_map_flag.setText("경상북도 안동시 당북동 91-1");
            text_map_call.setText("054-854-1415");
            text_map_open.setText("정보가 없습니다");
            text_map_time.setText("정보가 없습니다");
            call_status = true;
        }
        else if(maker_name.equals("알프스애견샵")){
            Glide.with(context).load(R.drawable.toy).fitCenter().into(map_img);
            text_map_flag.setText("경상북도 안동시 태화동 206-1");
            text_map_call.setText("054-855-7060");
            text_map_open.setText("정보가 없습니다");
            text_map_time.setText("정보가 없습니다");
            call_status = true;
        }
        else if(maker_name.equals("BOB애견용품백화점")){
            Glide.with(context).load(R.drawable.toy).fitCenter().into(map_img);
            text_map_flag.setText("울산광역시 남구 신정4동 810-4");
            text_map_call.setText("052-258-7735");
            text_map_open.setText("정보가 없습니다");
            text_map_time.setText("정보가 없습니다");
            call_status = true;
        }
        else if(maker_name.equals("펫마트울산삼산점")){
            Glide.with(context).load(R.drawable.toy).fitCenter().into(map_img);
            text_map_flag.setText("울산광역시 남구 신정4동 810-4");
            text_map_call.setText("052-271-1550");
            text_map_open.setText("정보가 없습니다");
            text_map_time.setText("오전 9:00~오후 10:00");
            call_status = true;
        }
        else if(maker_name.equals("펫마트양산물금점")){
            Glide.with(context).load(R.drawable.toy).fitCenter().into(map_img);
            text_map_flag.setText("경상남도 양산시 물금읍 가촌리 906-1");
            text_map_call.setText("055-367-1550");
            text_map_open.setText("정보가 없습니다");
            text_map_time.setText("오전 9:00~오후 10:00");
            call_status = true;
        }
        else if(maker_name.equals("범어애견")){
            Glide.with(context).load(R.drawable.toy).fitCenter().into(map_img);
            text_map_flag.setText("경상남도 양산시 물금읍 범어리 590-1");
            text_map_call.setText("055-388-0049");
            text_map_open.setText("정보가 없습니다");
            text_map_time.setText("정보가 없습니다");
            call_status = true;
        }
        else if(maker_name.equals("동양애견용품사료할인마트")){
            Glide.with(context).load(R.drawable.toy).fitCenter().into(map_img);
            text_map_flag.setText("부산광역시 금정구 구서2동 742-9");
            text_map_call.setText("051-515-4241");
            text_map_open.setText("정보가 없습니다");
            text_map_time.setText("정보가 없습니다");
            call_status = true;
        }
        else if(maker_name.equals("백평애견용품백화점")){
            Glide.with(context).load(R.drawable.toy).fitCenter().into(map_img);
            text_map_flag.setText("부산광역시 북구 화명3동 2292-3");
            text_map_call.setText("051-365-4900");
            text_map_open.setText("정보가 없습니다");
            text_map_time.setText("정보가 없습니다");
            call_status = true;
        }
        else if(maker_name.equals("펫마트남천점")){
            Glide.with(context).load(R.drawable.toy).fitCenter().into(map_img);
            text_map_flag.setText("부산광역시 수영구 남천1동 340-1");
            text_map_call.setText("051-625-1550");
            text_map_open.setText("http://www.petmart.kr/");
            text_map_time.setText("오전 9:00~오후 10:00");
            call_status = true;
            open_status = true;
        }
        else if(maker_name.equals("핫독애견용품")){
            Glide.with(context).load(R.drawable.toy).fitCenter().into(map_img);
            text_map_flag.setText("부산광역시 남구 용호동 370-32");
            text_map_call.setText("051-611-0177");
            text_map_open.setText("정보가 없습니다");
            text_map_time.setText("정보가 없습니다");
            call_status = true;
        }
        else if(maker_name.equals("애완동물메카마트")){
            Glide.with(context).load(R.drawable.toy).fitCenter().into(map_img);
            text_map_flag.setText("부산광역시 해운대구 중동 1360");
            text_map_call.setText("051-751-4884");
            text_map_open.setText("정보가 없습니다");
            text_map_time.setText("정보가 없습니다");
            call_status = true;
        }
        else if(maker_name.equals("사림누리애견")){
            Glide.with(context).load(R.drawable.toy).fitCenter().into(map_img);
            text_map_flag.setText("경상남도 창원시 의창구 사림동 43-15");
            text_map_call.setText("055-281-6377");
            text_map_open.setText("정보가 없습니다");
            text_map_time.setText("정보가 없습니다");
            call_status = true;
        }
        else if(maker_name.equals("프렌드애견")){
            Glide.with(context).load(R.drawable.toy).fitCenter().into(map_img);
            text_map_flag.setText("경상남도 거제시 고현동 760-8");
            text_map_call.setText("055-635-7982");
            text_map_open.setText("정보가 없습니다");
            text_map_time.setText("정보가 없습니다");
            call_status = true;
        }
        else if(maker_name.equals("복남이네애견")){
            Glide.with(context).load(R.drawable.toy).fitCenter().into(map_img);
            text_map_flag.setText("경상남도 통영시 무전동 1026-3");
            text_map_call.setText("055-649-7709");
            text_map_open.setText("정보가 없습니다");
            text_map_time.setText("정보가 없습니다");
            call_status = true;
        }
        else if(maker_name.equals("명품애견")){
            Glide.with(context).load(R.drawable.toy).fitCenter().into(map_img);
            text_map_flag.setText("경상남도 진주시 망경동 41-2");
            text_map_call.setText("055-759-7889");
            text_map_open.setText("정보가 없습니다");
            text_map_time.setText("정보가 없습니다");
            call_status = true;
        }
        else if(maker_name.equals("행복애견샵")){
            Glide.with(context).load(R.drawable.toy).fitCenter().into(map_img);
            text_map_flag.setText("경상남도 진주시 수정동 21-15");
            text_map_call.setText("055-747-8919");
            text_map_open.setText("정보가 없습니다");
            text_map_time.setText("정보가 없습니다");
            call_status = true;
        }
        else if(maker_name.equals("도그몰 애견종합할인마트 본점")){
            Glide.with(context).load(R.drawable.toy).fitCenter().into(map_img);
            text_map_flag.setText("전라남도 광양시 중동 1308-13 KR");
            text_map_call.setText("061-791-8944");
            text_map_open.setText("https://blog.naver.com/dogmall365");
            text_map_time.setText("오전 9:00~오후 10:00");
            call_status = true;
            open_status = true;
        }
        else if(maker_name.equals("카니네펫샵")){
            Glide.with(context).load(R.drawable.toy).fitCenter().into(map_img);
            text_map_flag.setText("전라남도 광양시 중동 1393-3");
            text_map_call.setText("061-793-0430");
            text_map_open.setText("정보가 없습니다");
            text_map_time.setText("정보가 없습니다");
            call_status = true;
        }
        else if(maker_name.equals("펫마트 순천법원")){
            Glide.with(context).load(R.drawable.toy).fitCenter().into(map_img);
            text_map_flag.setText("전라남도 순천시 왕지동 849-2");
            text_map_call.setText("061-722-1550");
            text_map_open.setText("http://www.petmart.kr/");
            text_map_time.setText("오전 10:00~오후 10:00");
            call_status = true;
        }
        else if(maker_name.equals("애견풀하우스")){
            Glide.with(context).load(R.drawable.toy).fitCenter().into(map_img);
            text_map_flag.setText("전라남도 순천시 연향동 1377-10");
            text_map_call.setText("061-727-3007");
            text_map_open.setText("정보가 없습니다");
            text_map_time.setText("정보가 없습니다");
            call_status = true;
        }
        else if(maker_name.equals("엔젤애견")){
            Glide.with(context).load(R.drawable.toy).fitCenter().into(map_img);
            text_map_flag.setText("전라남도 목포시 상동 787-7");
            text_map_call.setText("061-281-0921");
            text_map_open.setText("정보가 없습니다");
            text_map_time.setText("정보가 없습니다");
            call_status = true;
        }
        else if(maker_name.equals("나주애견랜드")){
            Glide.with(context).load(R.drawable.toy).fitCenter().into(map_img);
            text_map_flag.setText("전라남도 나주시 금성동 30-1");
            text_map_call.setText("061-334-8980");
            text_map_open.setText("정보가 없습니다");
            text_map_time.setText("정보가 없습니다");
            call_status = true;
        }
        else if(maker_name.equals("진월애견편의방")){
            Glide.with(context).load(R.drawable.toy).fitCenter().into(map_img);
            text_map_flag.setText("광주광역시 남구 진월동 410-5");
            text_map_call.setText("062-652-0232");
            text_map_open.setText("정보가 없습니다");
            text_map_time.setText("정보가 없습니다");
            call_status = true;
        }
        else if(maker_name.equals("소문난강아지")){
            Glide.with(context).load(R.drawable.toy).fitCenter().into(map_img);
            text_map_flag.setText("광주광역시 서구 금호동 733-13");
            text_map_call.setText("062-372-8533");
            text_map_open.setText("정보가 없습니다");
            text_map_time.setText("정보가 없습니다");
            call_status = true;
        }
        else if(maker_name.equals("에바다애견")){
            Glide.with(context).load(R.drawable.toy).fitCenter().into(map_img);
            text_map_flag.setText("광주광역시 남구 구동 37-4");
            text_map_call.setText("062-672-2122");
            text_map_open.setText("정보가 없습니다");
            text_map_time.setText("정보가 없습니다");
            call_status = true;
        }
        else if(maker_name.equals("한빛애견")){
            Glide.with(context).load(R.drawable.toy).fitCenter().into(map_img);
            text_map_flag.setText("광주광역시 서구 양동 25-44");
            text_map_call.setText("062-368-6218");
            text_map_open.setText("정보가 없습니다");
            text_map_time.setText("정보가 없습니다");
            call_status = true;
        }
        else if(maker_name.equals("빅펫하우스")){
            Glide.with(context).load(R.drawable.toy).fitCenter().into(map_img);
            text_map_flag.setText("광주광역시 남구 주월동 서문대로 801");
            text_map_call.setText("062-654-8584");
            text_map_open.setText("http://www.bigpet.kr/");
            text_map_time.setText("정보가 없습니다");
            call_status = true;
        }
        else if(maker_name.equals("서강애견샵")){
            Glide.with(context).load(R.drawable.toy).fitCenter().into(map_img);
            text_map_flag.setText("광주광역시 북구 운암동 925");
            text_map_call.setText("062-521-0907");
            text_map_open.setText("정보가 없습니다");
            text_map_time.setText("정보가 없습니다");
            call_status = true;
        }
        else if(maker_name.equals("애견용품할인점")){
            Glide.with(context).load(R.drawable.toy).fitCenter().into(map_img);
            text_map_flag.setText("광주광역시 북구 매곡동 32-2");
            text_map_call.setText("062-573-2316");
            text_map_open.setText("정보가 없습니다");
            text_map_time.setText("정보가 없습니다");
            call_status = true;
        }
        else if(maker_name.equals("애견하우스 광주")){
            Glide.with(context).load(R.drawable.toy).fitCenter().into(map_img);
            text_map_flag.setText("광주광역시 북구 용봉동 1274-1");
            text_map_call.setText("062-575-8014");
            text_map_open.setText("정보가 없습니다");
            text_map_time.setText("정보가 없습니다");
            call_status = true;
        }
        else if(maker_name.equals("멍멍아")){
            Glide.with(context).load(R.drawable.toy).fitCenter().into(map_img);
            text_map_flag.setText("광주광역시 광산구 신가동 1001-1");
            text_map_call.setText("062-942-0097");
            text_map_open.setText("정보가 없습니다");
            text_map_time.setText("정보가 없습니다");
            call_status = true;
        }
        else if(maker_name.equals("펫플러스")){
            Glide.with(context).load(R.drawable.toy).fitCenter().into(map_img);
            text_map_flag.setText("광주광역시 광산구 쌍암동 694-93");
            text_map_call.setText("062-974-4582");
            text_map_open.setText("정보가 없습니다");
            text_map_time.setText("정보가 없습니다");
            call_status = true;
        }
        else if(maker_name.equals("패밀리애견")){
            Glide.with(context).load(R.drawable.toy).fitCenter().into(map_img);
            text_map_flag.setText("광주광역시 광산구 쌍암동 662-1");
            text_map_call.setText("062-973-7888");
            text_map_open.setText("정보가 없습니다");
            text_map_time.setText("정보가 없습니다");
            call_status = true;
        }
        else if(maker_name.equals("펫스토리")){
            Glide.with(context).load(R.drawable.toy).fitCenter().into(map_img);
            text_map_flag.setText("광주광역시 광산구 장덕동");
            text_map_call.setText("062-955-0279");
            text_map_open.setText("정보가 없습니다");
            text_map_time.setText("정보가 없습니다");
            call_status = true;
        }
        else if(maker_name.equals("강아지천국")){
            Glide.with(context).load(R.drawable.toy).fitCenter().into(map_img);
            text_map_flag.setText("전라북도 정읍시 수성동 706-1");
            text_map_call.setText("063-538-0199");
            text_map_open.setText("정보가 없습니다");
            text_map_time.setText("정보가 없습니다");
            call_status = true;
        }
        else if(maker_name.equals("강아지나라 김제시")){
            Glide.with(context).load(R.drawable.toy).fitCenter().into(map_img);
            text_map_flag.setText("전라북도 김제시 요촌동 514-16 KR");
            text_map_call.setText("063-548-5222");
            text_map_open.setText("http://dogcountry.7x7.kr/");
            text_map_time.setText("월~토 : 오전 9:00~오후 10:00\n일요일 : 오후 12:00~6:00");
            call_status = true;
        }
        else if(maker_name.equals("애견백화점")){
            Glide.with(context).load(R.drawable.toy).fitCenter().into(map_img);
            text_map_flag.setText("전라북도 김제시 요촌동 83-1");
            text_map_call.setText("063-542-0262");
            text_map_open.setText("정보가 없습니다");
            text_map_time.setText("정보가 없습니다");
            call_status = true;
        }
        else if(maker_name.equals("펫마트송천에코점")){
            Glide.with(context).load(R.drawable.toy).fitCenter().into(map_img);
            text_map_flag.setText("전라북도 전주시 덕진구 송천동2가 453-2");
            text_map_call.setText("063-278-1550");
            text_map_open.setText("https://www.instagram.com/petmarteco/");
            text_map_time.setText("월~토 : 오전 9:00~오후 10:00\n" +
                    "일요일 : 오전 10:00~오후 10:00");
            call_status = true;
        }
        else if(maker_name.equals("올리브애견")){
            Glide.with(context).load(R.drawable.toy).fitCenter().into(map_img);
            text_map_flag.setText("전라북도 전주시 덕진구 송천동1가 386-3");
            text_map_call.setText("063-253-9500");
            text_map_open.setText("정보가 없습니다");
            text_map_time.setText("정보가 없습니다");
            call_status = true;
        }
        else if(maker_name.equals("펫마트 전주효자점")){
            Glide.with(context).load(R.drawable.toy).fitCenter().into(map_img);
            text_map_flag.setText("전라북도 전주시 완산구 효자동2가 거마평로 235");
            text_map_call.setText("063-254-1550");
            text_map_open.setText("정보가 없습니다");
            text_map_time.setText("월~토 : 오전 9:00~오후 10:00\n" +
                    "일요일 : 오전 10:00~오후 10:00");
            call_status = true;
        }
        else if(maker_name.equals("펫마트 전주평화점")){
            Glide.with(context).load(R.drawable.toy).fitCenter().into(map_img);
            text_map_flag.setText("전라북도 전주시 완산구 효자동2가 거마평로 235");
            text_map_call.setText("063-282-1550");
            text_map_open.setText("정보가 없습니다");
            text_map_time.setText("월~토 : 오전 9:00~오후 10:00\n" +
                    "일요일 : 오전 10:00~오후 10:00");
            call_status = true;
        }
        else if(maker_name.equals("나나애견")){
            Glide.with(context).load(R.drawable.toy).fitCenter().into(map_img);
            text_map_flag.setText("전라북도 전주시 완산구 효자동1가 403-3");
            text_map_call.setText("063-237-1760");
            text_map_open.setText("정보가 없습니다");
            text_map_time.setText("정보가 없습니다");
            call_status = true;
        }
        else if(maker_name.equals("애견코리아 익산")){
            Glide.with(context).load(R.drawable.toy).fitCenter().into(map_img);
            text_map_flag.setText("전라북도 익산시 창인동1가 49-23");
            text_map_call.setText("063-857-2298");
            text_map_open.setText("정보가 없습니다");
            text_map_time.setText("정보가 없습니다");
            call_status = true;
        }
        else if(maker_name.equals("동양애견백화점")){
            Glide.with(context).load(R.drawable.toy).fitCenter().into(map_img);
            text_map_flag.setText("충청남도 논산시 부창동 29-4");
            text_map_call.setText("041-736-5989");
            text_map_open.setText("정보가 없습니다");
            text_map_time.setText("정보가 없습니다");
            call_status = true;
        }
        else if(maker_name.equals("야옹아멍멍해봐")){
            Glide.with(context).load(R.drawable.toy).fitCenter().into(map_img);
            text_map_flag.setText("충청남도 논산시 취암동 중앙로 427");
            text_map_call.setText("041-732-9195");
            text_map_open.setText("정보가 없습니다");
            text_map_time.setText("정보가 없습니다");
            call_status = true;
        }
        else if(maker_name.equals("공주나라강쥐")){
            Glide.with(context).load(R.drawable.toy).fitCenter().into(map_img);
            text_map_flag.setText("대전광역시 서구 관저동");
            text_map_call.setText("042-541-5444");
            text_map_open.setText("정보가 없습니다");
            text_map_time.setText("정보가 없습니다");
            call_status = true;
        }
        else if(maker_name.equals("양옹아 멍멍해봐")){
            Glide.with(context).load(R.drawable.toy).fitCenter().into(map_img);
            text_map_flag.setText("대전광역시 서구 관저동 관저중로96번길 21");
            text_map_call.setText("042-544-0048");
            text_map_open.setText("정보가 없습니다");
            text_map_time.setText("정보가 없습니다");
            call_status = true;
        }
        else if(maker_name.equals("대청애견")){
            Glide.with(context).load(R.drawable.toy).fitCenter().into(map_img);
            text_map_flag.setText("대전광역시 서구 도마1동 61-6");
            text_map_call.setText("042-532-5574");
            text_map_open.setText("정보가 없습니다");
            text_map_time.setText("정보가 없습니다");
            call_status = true;
        }
        else if(maker_name.equals("예쁜강아지")){
            Glide.with(context).load(R.drawable.toy).fitCenter().into(map_img);
            text_map_flag.setText("대전광역시 서구 도마2동 128-5");
            text_map_call.setText("042-535-7552");
            text_map_open.setText("정보가 없습니다");
            text_map_time.setText("정보가 없습니다");
            call_status = true;
        }
        else if(maker_name.equals("우아펫")){
            Glide.with(context).load(R.drawable.toy).fitCenter().into(map_img);
            text_map_flag.setText("대전광역시 서구 괴정동");
            text_map_call.setText("042-525-7942");
            text_map_open.setText("정보가 없습니다");
            text_map_time.setText("오전 9:00~오후 9:00(화요일 휴무)");
            call_status = true;
        }
        else if(maker_name.equals("진주애견마트")){
            Glide.with(context).load(R.drawable.toy).fitCenter().into(map_img);
            text_map_flag.setText("대전광역시 중구 중촌동 117-14");
            text_map_call.setText("042-252-4630");
            text_map_open.setText("정보가 없습니다");
            text_map_time.setText("정보가 없습니다");
            call_status = true;
        }
        else if(maker_name.equals("소문난강아지 대전")){
            Glide.with(context).load(R.drawable.toy).fitCenter().into(map_img);
            text_map_flag.setText("대전광역시 중구 오류동 179-30");
            text_map_call.setText("042-533-0907");
            text_map_open.setText("정보가 없습니다");
            text_map_time.setText("정보가 없습니다");
            call_status = true;
        }
        else if(maker_name.equals("삼성애견")){
            Glide.with(context).load(R.drawable.toy).fitCenter().into(map_img);
            text_map_flag.setText("대전광역시 중구 오류동 155-1");
            text_map_call.setText("042-524-8116");
            text_map_open.setText("정보가 없습니다");
            text_map_time.setText("정보가 없습니다");
            call_status = true;
        }
        else if(maker_name.equals("누리애견샵")){
            Glide.with(context).load(R.drawable.toy).fitCenter().into(map_img);
            text_map_flag.setText("대전광역시 동구 가오동 667");
            text_map_call.setText("042-272-7848");
            text_map_open.setText("정보가 없습니다");
            text_map_time.setText("정보가 없습니다");
            call_status = true;
        }
        else if(maker_name.equals("명품애견 대전")){
            Glide.with(context).load(R.drawable.toy).fitCenter().into(map_img);
            text_map_flag.setText("대전광역시 유성구 관평동 1004");
            text_map_call.setText("042-671-0209");
            text_map_open.setText("정보가 없습니다");
            text_map_time.setText("정보가 없습니다");
            call_status = true;
        }
        else if(maker_name.equals("나라애견 청주")){
            Glide.with(context).load(R.drawable.toy).fitCenter().into(map_img);
            text_map_flag.setText("충청북도 청주시 흥덕구 가경동 1353");
            text_map_call.setText("043-233-9933");
            text_map_open.setText("정보가 없습니다");
            text_map_time.setText("정보가 없습니다");
            call_status = true;
        }
        else if(maker_name.equals("심플펫")){
            Glide.with(context).load(R.drawable.toy).fitCenter().into(map_img);
            text_map_flag.setText("충청남도 천안시 동남구 신방동 풍세로 769-39");
            text_map_call.setText("041-555-1440");
            text_map_open.setText("http://www.simplepet.co.kr/");
            text_map_time.setText("정보가 없습니다");
            call_status = true;
        }
        else if(maker_name.equals("웃는강아지")){
            Glide.with(context).load(R.drawable.toy).fitCenter().into(map_img);
            text_map_flag.setText("충청남도 천안시 동남구 신방동 72-6");
            text_map_call.setText("041-571-3005");
            text_map_open.setText("정보가 없습니다");
            text_map_time.setText("정보가 없습니다");
            call_status = true;
        }
        else if(maker_name.equals("마이펫애견용품할인점")){
            Glide.with(context).load(R.drawable.toy).fitCenter().into(map_img);
            text_map_flag.setText("충청남도 천안시 서북구 성정2동");
            text_map_call.setText("041-579-8774");
            text_map_open.setText("정보가 없습니다");
            text_map_time.setText("정보가 없습니다");
            call_status = true;
        }
        else if(maker_name.equals("아싸애견용품할인매장")){
            Glide.with(context).load(R.drawable.toy).fitCenter().into(map_img);
            text_map_flag.setText("서북구 백석동 872번지 109호 천안시 충청남도");
            text_map_call.setText("041-523-7001");
            text_map_open.setText("정보가 없습니다");
            text_map_time.setText("정보가 없습니다");
            call_status = true;
        }
        else if(maker_name.equals("애견하우스 당진")){
            Glide.with(context).load(R.drawable.toy).fitCenter().into(map_img);
            text_map_flag.setText("충청남도 당진시 신평면 거산리 163-9");
            text_map_call.setText("041-362-5552");
            text_map_open.setText("정보가 없습니다");
            text_map_time.setText("정보가 없습니다");
            call_status = true;
        }
        else if(maker_name.equals("조이펫(애견용품할인매장)")){
            Glide.with(context).load(R.drawable.toy).fitCenter().into(map_img);
            text_map_flag.setText("경기도 화성시 향남읍 평리 82-3");
            text_map_call.setText("031-353-7409");
            text_map_open.setText("정보가 없습니다");
            text_map_time.setText("정보가 없습니다");
            call_status = true;
        }
        else if(maker_name.equals("뽀뽀애견샵")){
            Glide.with(context).load(R.drawable.toy).fitCenter().into(map_img);
            text_map_flag.setText("경기도 화성시 봉담읍 상리 33-1");
            text_map_call.setText("031-242-2053");
            text_map_open.setText("정보가 없습니다");
            text_map_time.setText("정보가 없습니다");
            call_status = true;
        }
        else if(maker_name.equals("애견샵 어린왕자")){
            Glide.with(context).load(R.drawable.toy).fitCenter().into(map_img);
            text_map_flag.setText("경기도 화성시 안녕동 37-22");
            text_map_call.setText("031-231-2859");
            text_map_open.setText("정보가 없습니다");
            text_map_time.setText("정보가 없습니다");
            call_status = true;
        }
        else if(maker_name.equals("갤럭시펫")){
            Glide.with(context).load(R.drawable.toy).fitCenter().into(map_img);
            text_map_flag.setText("경기도 화성시 반월동 135-10");
            text_map_call.setText("1899-7946");
            text_map_open.setText("http://www.galaxy-pet.com/");
            text_map_time.setText("오전 10:00~오후 9:00");
            call_status = true;
            open_status = true;
        }
        else if(maker_name.equals("노을애견")){
            Glide.with(context).load(R.drawable.toy).fitCenter().into(map_img);
            text_map_flag.setText("경기도 수원시 영통구 매탄4동 208-19");
            text_map_call.setText("031-213-7717");
            text_map_open.setText("정보가 없습니다");
            text_map_time.setText("정보가 없습니다");
            call_status = true;
        }
        else if(maker_name.equals("요술강아지")){
            Glide.with(context).load(R.drawable.toy).fitCenter().into(map_img);
            text_map_flag.setText("경기도 수원시 영통구 매탄동 1253-5");
            text_map_call.setText("031-217-1849");
            text_map_open.setText("정보가 없습니다");
            text_map_time.setText("정보가 없습니다");
            call_status = true;
        }
        else if(maker_name.equals("체리애견")){
            Glide.with(context).load(R.drawable.toy).fitCenter().into(map_img);
            text_map_flag.setText("경기도 수원시 팔달구 인계동 1003-25");
            text_map_call.setText("031-224-7977");
            text_map_open.setText("정보가 없습니다");
            text_map_time.setText("정보가 없습니다");
            call_status = true;
        }
        else if(maker_name.equals("행운애견")){
            Glide.with(context).load(R.drawable.toy).fitCenter().into(map_img);
            text_map_flag.setText("경기도 수원시 팔달구 중동 74-1");
            text_map_call.setText("031-246-7123");
            text_map_open.setText("정보가 없습니다");
            text_map_time.setText("정보가 없습니다");
            call_status = true;
        }
        else if(maker_name.equals("강아지세상")){
            Glide.with(context).load(R.drawable.toy).fitCenter().into(map_img);
            text_map_flag.setText("경기도 수원시 권선구 호매실동 80-1");
            text_map_call.setText("031-298-0353");
            text_map_open.setText("정보가 없습니다");
            text_map_time.setText("정보가 없습니다");
            call_status = true;
        }
        else if(maker_name.equals("스타애견")){
            Glide.with(context).load(R.drawable.toy).fitCenter().into(map_img);
            text_map_flag.setText("경기도 수원시 팔달구 우만동 485");
            text_map_call.setText("031-268-0997");
            text_map_open.setText("정보가 없습니다");
            text_map_time.setText("정보가 없습니다");
            call_status = true;
        }
        else if(maker_name.equals("강아지가정부")){
            Glide.with(context).load(R.drawable.toy).fitCenter().into(map_img);
            text_map_flag.setText("경기도 의왕시 삼동 125");
            text_map_call.setText("031-462-5777");
            text_map_open.setText("정보가 없습니다");
            text_map_time.setText("정보가 없습니다");
            call_status = true;
        }
        else if(maker_name.equals("똥강아지")){
            Glide.with(context).load(R.drawable.toy).fitCenter().into(map_img);
            text_map_flag.setText("경기도 안산시 상록구 이동 605-3");
            text_map_call.setText("031-438-0329");
            text_map_open.setText("정보가 없습니다");
            text_map_time.setText("정보가 없습니다");
            call_status = true;
        }
        else if(maker_name.equals("해피애견 안산")){
            Glide.with(context).load(R.drawable.toy).fitCenter().into(map_img);
            text_map_flag.setText("경기도 안산시 상록구 본오2동 871-4");
            text_map_call.setText("031-419-3377");
            text_map_open.setText("정보가 없습니다");
            text_map_time.setText("정보가 없습니다");
            call_status = true;
        }
        else if(maker_name.equals("프로펫애견삽")){
            Glide.with(context).load(R.drawable.toy).fitCenter().into(map_img);
            text_map_flag.setText("경기도 시흥시 정왕동 1214-8");
            text_map_call.setText("031-499-7785");
            text_map_open.setText("정보가 없습니다");
            text_map_time.setText("정보가 없습니다");
            call_status = true;
        }
        else if(maker_name.equals("벤지애견백화점")){
            Glide.with(context).load(R.drawable.toy).fitCenter().into(map_img);
            text_map_flag.setText("인천광역시 남구 용현동 622-112");
            text_map_call.setText("032-882-6592");
            text_map_open.setText("정보가 없습니다");
            text_map_time.setText("정보가 없습니다");
            call_status = true;
        }
        else if(maker_name.equals("애견용품힐인매장 인천")){
            Glide.with(context).load(R.drawable.toy).fitCenter().into(map_img);
            text_map_flag.setText("주안동 153-3번지 지하1층 남구 인천광역시");
            text_map_call.setText("정보가 없습니다");
            text_map_open.setText("정보가 없습니다");
            text_map_time.setText("정보가 없습니다");

        }
        else if(maker_name.equals("도그피아 시흥")){
            Glide.with(context).load(R.drawable.toy).fitCenter().into(map_img);
            text_map_flag.setText("경기도 시흥시 대야동 계수로197번길 16");
            text_map_call.setText("1544-3563");
            text_map_open.setText("http://www.dogpia.net/");
            text_map_time.setText("월~금 : 오전 9:00~오후 6:00\n토 : 오전 9:00~오후 2:00(일 휴무)");
            call_status=true;
            open_status=true;
        }
        else if(maker_name.equals("애견백화점 강릉")){
            Glide.with(context).load(R.drawable.toy).fitCenter().into(map_img);
            text_map_flag.setText("강원도 강릉시 주문진읍 교항리 119");
            text_map_call.setText("033-661-1169");
            text_map_open.setText("정보가 없습니다");
            text_map_time.setText("정보가 없습니다");
            call_status=true;
        }
        else if(maker_name.equals("펫클럽 강릉점")){
            Glide.with(context).load(R.drawable.toy).fitCenter().into(map_img);
            text_map_flag.setText("강원도 강릉시 교동 162-183");
            text_map_call.setText("070-4675-2266");
            text_map_open.setText("http://petclub.co.kr/");
            text_map_time.setText("오전 10:00~오후 9:00");
            call_status=true;
            open_status=true;
        }
        else if(maker_name.equals("애견용품 대형백화점")){
            Glide.with(context).load(R.drawable.toy).fitCenter().into(map_img);
            text_map_flag.setText("강원도 강릉시 교동 156-68");
            text_map_call.setText("033-642-7337");
            text_map_open.setText("정보가 없습니다");
            text_map_time.setText("정보가 없습니다");
            call_status=true;
        }
        else if(maker_name.equals("러브펫 강릉")){
            Glide.with(context).load(R.drawable.toy).fitCenter().into(map_img);
            text_map_flag.setText("강원도 강릉시 옥천동 158-3");
            text_map_call.setText("033-643-2234");
            text_map_open.setText("정보가 없습니다");
            text_map_time.setText("정보가 없습니다");
            call_status=true;
        }
        else if(maker_name.equals("나라애견 강릉")){
            Glide.with(context).load(R.drawable.toy).fitCenter().into(map_img);
            text_map_flag.setText("강원도 강릉시 옥천동 경강로 2138-1");
            text_map_call.setText("033-647-0072");
            text_map_open.setText("정보가 없습니다");
            text_map_time.setText("정보가 없습니다");
            call_status=true;
        }
        else if(maker_name.equals("주주클럽 동해")){
            Glide.with(context).load(R.drawable.toy).fitCenter().into(map_img);
            text_map_flag.setText("강원도 동해시 발한동 573-1");
            text_map_call.setText("033-533-4840");
            text_map_open.setText("정보가 없습니다");
            text_map_time.setText("정보가 없습니다");
            call_status=true;
        }
        else if(maker_name.equals("서울애견 동해")){
            Glide.with(context).load(R.drawable.toy).fitCenter().into(map_img);
            text_map_flag.setText("강원도 동해시 천곡동 1021");
            text_map_call.setText("033-533-0643");
            text_map_open.setText("정보가 없습니다");
            text_map_time.setText("정보가 없습니다");
            call_status=true;
        }
        else if(maker_name.equals("강아지와고양이 동해")){
            Glide.with(context).load(R.drawable.toy).fitCenter().into(map_img);
            text_map_flag.setText("강원도 동해시 천곡동 1022");
            text_map_call.setText("033-533-3133");
            text_map_open.setText("정보가 없습니다");
            text_map_time.setText("정보가 없습니다");
            call_status=true;
        }
        else if(maker_name.equals("행복한강아지 삼척")){
            Glide.with(context).load(R.drawable.toy).fitCenter().into(map_img);
            text_map_flag.setText("강원도 삼척시 남양동 129-3");
            text_map_call.setText("033-573-3434");
            text_map_open.setText("정보가 없습니다");
            text_map_time.setText("정보가 없습니다");
            call_status=true;
        }
        else if(maker_name.equals("위드펫애견용품할인점")){
            Glide.with(context).load(R.drawable.toy).fitCenter().into(map_img);
            text_map_flag.setText("강원도 삼척시 당저동 54-2번지 1층");
            text_map_call.setText("033-648-5583");
            text_map_open.setText("정보가 없습니다");
            text_map_time.setText("정보가 없습니다");
            call_status=true;
        }
        else if(maker_name.equals("강아지반상회")){
            Glide.with(context).load(R.drawable.toy).fitCenter().into(map_img);
            text_map_flag.setText("경상북도 포항시 남구 상도동 중흥로 25");
            text_map_call.setText("054-278-1550");
            text_map_open.setText("정보가 없습니다");
            text_map_time.setText("정보가 없습니다");
            call_status=true;
        }
        else if(maker_name.equals("펫마트 포항상도점")){
            Glide.with(context).load(R.drawable.toy).fitCenter().into(map_img);
            text_map_flag.setText("경상북도 포항시 남구 상도동 중흥로 25");
            text_map_call.setText("054-278-1550");
            text_map_open.setText("http://www.petmart.kr/");
            text_map_time.setText("오전 9:00~오후 10:00");
            call_status=true;
            open_status=true;
        }
        else if(maker_name.equals("펫마트 포항상도")){
            Glide.with(context).load(R.drawable.toy).fitCenter().into(map_img);
            text_map_flag.setText("경상북도 포항시 남구 상도동 684");
            text_map_call.setText("054-278-1550");
            text_map_open.setText("http://www.petmart.kr/");
            text_map_time.setText("오전 9:00~오후 10:00");
            call_status=true;
            open_status=true;
        }
        else if(maker_name.equals("해피애견타운 포항 남구점")){
            Glide.with(context).load(R.drawable.toy).fitCenter().into(map_img);
            text_map_flag.setText("상도동 601-14 2층 남구 포항시 경상북도 KR");
            text_map_call.setText("054-286-2006");
            text_map_open.setText("http://www.happytown.kr/");
            text_map_time.setText("오전 10:00~오후 10:00");
            call_status=true;
            open_status=true;
        }
        else if(maker_name.equals("스누피애견 포항")){
            Glide.with(context).load(R.drawable.toy).fitCenter().into(map_img);
            text_map_flag.setText("경상북도 포항시 남구 대잠동 938");
            text_map_call.setText("054-276-5809");
            text_map_open.setText("정보가 없습니다");
            text_map_time.setText("정보가 없습니다");
            call_status=true;
        }
        else if(maker_name.equals("펫마트 포항점")){
            Glide.with(context).load(R.drawable.toy).fitCenter().into(map_img);
            text_map_flag.setText("경상북도 포항시 남구 해도동 34-9");
            text_map_call.setText("054-255-1550");
            text_map_open.setText("정보가 없습니다");
            text_map_time.setText("오전 9:00~오후 10:00");
            call_status=true;
        }
        else if(maker_name.equals("강아지나라 포항")){
            Glide.with(context).load(R.drawable.toy).fitCenter().into(map_img);
            text_map_flag.setText("경상북도 포항시 북구 대흥동 620-9");
            text_map_call.setText("054-282-9828");
            text_map_open.setText("정보가 없습니다");
            text_map_time.setText("정보가 없습니다");
            call_status=true;
        }
        else if(maker_name.equals("포항애견")){
            Glide.with(context).load(R.drawable.toy).fitCenter().into(map_img);
            text_map_flag.setText("경상북도 포항시 북구 대흥동 619-24");
            text_map_call.setText("054-272-3340");
            text_map_open.setText("정보가 없습니다");
            text_map_time.setText("정보가 없습니다");
            call_status=true;
        }
        else if(maker_name.equals("펫마트 포항")){
            Glide.with(context).load(R.drawable.toy).fitCenter().into(map_img);
            text_map_flag.setText("경북 포항시 북구 용흥2동 625-2");
            text_map_call.setText("054-272-0745");
            text_map_open.setText("정보가 없습니다");
            text_map_time.setText("정보가 없습니다");
            call_status=true;
        }
        else if(maker_name.equals("황제애견")){
            Glide.with(context).load(R.drawable.toy).fitCenter().into(map_img);
            text_map_flag.setText("경상북도 포항시 북구 신흥동 693-11");
            text_map_call.setText("054-248-8820");
            text_map_open.setText("정보가 없습니다");
            text_map_time.setText("정보가 없습니다");
            call_status=true;
        }
        else if(maker_name.equals("펫마트 경주화랑점")){
            Glide.with(context).load(R.drawable.toy).fitCenter().into(map_img);
            text_map_flag.setText("경상북도 경주시 성건동 411-14");
            text_map_call.setText("054-772-1550");
            text_map_open.setText("http://www.petmart.kr/");
            text_map_time.setText("오전 10:00~오후 10:00");
            open_status=true;
            call_status=true;
        }
        else if(maker_name.equals("황제애견 경주")){
            Glide.with(context).load(R.drawable.toy).fitCenter().into(map_img);
            text_map_flag.setText("경상북도 경주시 성건동 211-9");
            text_map_call.setText("054-771-3411");
            text_map_open.setText("정보가 없습니다");
            text_map_time.setText("정보가 없습니다");
            call_status=true;
        }
        else if(maker_name.equals("우리애견 경주")){
            Glide.with(context).load(R.drawable.toy).fitCenter().into(map_img);
            text_map_flag.setText("경상북도 경주시 성동동 201-3");
            text_map_call.setText("054-772-5088");
            text_map_open.setText("정보가 없습니다");
            text_map_time.setText("정보가 없습니다");
            call_status=true;
        }
        else if(maker_name.equals("펫마트경주점")){
            Glide.with(context).load(R.drawable.toy).fitCenter().into(map_img);
            text_map_flag.setText("경상북도 경주시 용강동 1174-11");
            text_map_call.setText("054-775-1550");
            text_map_open.setText("정보가 없습니다");
            text_map_time.setText("오전 10:00~오후 10:00");
            call_status=true;
        }
        else if(maker_name.equals("꿈꾸는강아지")){
            Glide.with(context).load(R.drawable.toy).fitCenter().into(map_img);
            text_map_flag.setText("경상북도 경주시 황성동 255-3");
            text_map_call.setText("054-773-0079");
            text_map_open.setText("정보가 없습니다");
            text_map_time.setText("정보가 없습니다");
            call_status=true;
        }
        else if(maker_name.equals("멍멍아놀자")){
            Glide.with(context).load(R.drawable.toy).fitCenter().into(map_img);
            text_map_flag.setText("울산광역시 울주군 범서읍 구영리 411-1");
            text_map_call.setText("052-248-0054");
            text_map_open.setText("정보가 없습니다");
            text_map_time.setText("정보가 없습니다");
            call_status=true;
        }
        else if(maker_name.equals("도그앤피플")){
            Glide.with(context).load(R.drawable.toy).fitCenter().into(map_img);
            text_map_flag.setText("울산광역시 남구 무거동 대학로 127");
            text_map_call.setText("052-247-7768");
            text_map_open.setText("정보가 없습니다");
            text_map_time.setText("정보가 없습니다");
            call_status=true;
        }
        else if(maker_name.equals("신운미애견미용")){
            Glide.with(context).load(R.drawable.toy).fitCenter().into(map_img);
            text_map_flag.setText("울산광역시 남구 신정1동 519-16");
            text_map_call.setText("052-900-2500");
            text_map_open.setText("정보가 없습니다");
            text_map_time.setText("정보가 없습니다");
            call_status=true;
        }
        else if(maker_name.equals("스타멍스")){
            Glide.with(context).load(R.drawable.toy).fitCenter().into(map_img);
            text_map_flag.setText("울산광역시 남구 신정동");
            text_map_call.setText("052-260-2991");
            text_map_open.setText("정보가 없습니다");
            text_map_time.setText("오전 10:00~오후 8:30");
            call_status=true;
        }
        else if(maker_name.equals("애견스케치")){
            Glide.with(context).load(R.drawable.toy).fitCenter().into(map_img);
            text_map_flag.setText("울산광역시 남구 신정4동 1836-12");
            text_map_call.setText("052-271-0049");
            text_map_open.setText("정보가 없습니다");
            text_map_time.setText("정보가 없습니다");
            call_status=true;
        }
        else if(maker_name.equals("아라애견 영주")){
            Glide.with(context).load(R.drawable.toy).fitCenter().into(map_img);
            text_map_flag.setText("경상북도 영주시 영주2동 505-7");
            text_map_call.setText("054-636-6346");
            text_map_open.setText("정보가 없습니다");
            text_map_time.setText("정보가 없습니다");
            call_status=true;
        }
        else if(maker_name.equals("애견용품전문매장 영주")){
            Glide.with(context).load(R.drawable.toy).fitCenter().into(map_img);
            text_map_flag.setText("경상북도 영주시 가흥1동 1454-19");
            text_map_call.setText("054-636-8881");
            text_map_open.setText("정보가 없습니다");
            text_map_time.setText("정보가 없습니다");
            call_status=true;
        }
        else if(maker_name.equals("작은동물원")){
            Glide.with(context).load(R.drawable.toy).fitCenter().into(map_img);
            text_map_flag.setText("경상북도 영천시 문내동 152-25");
            text_map_call.setText("054-331-0682");
            text_map_open.setText("정보가 없습니다");
            text_map_time.setText("정보가 없습니다");
            call_status=true;
        }
        else if(maker_name.equals("행복한강아지나라")){
            Glide.with(context).load(R.drawable.toy).fitCenter().into(map_img);
            text_map_flag.setText("경상북도 경산시 하양읍 동서리 592-44");
            text_map_call.setText("053-854-8944");
            text_map_open.setText("정보가 없습니다");
            text_map_time.setText("정보가 없습니다");
            call_status=true;
        }
        else if(maker_name.equals("더펫샵 경산점")){
            Glide.with(context).load(R.drawable.toy).fitCenter().into(map_img);
            text_map_flag.setText("경상북도 경산시 옥산동 경산로 227");
            text_map_call.setText("070-4348-1447");
            text_map_open.setText("https://pf.kakao.com/_gTFsC");
            text_map_time.setText("오전 9:00~오후 10:00");
            call_status=true;
            open_status=true;
        }
        else if(maker_name.equals("내사랑애견")){
            Glide.with(context).load(R.drawable.toy).fitCenter().into(map_img);
            text_map_flag.setText("경상북도 경산시 옥산동 726-3");
            text_map_call.setText("053-812-8588");
            text_map_open.setText("정보가 없습니다");
            text_map_time.setText("정보가 없습니다");
            call_status=true;
        }
        else if(maker_name.equals("펫마트딜라잇")){
            Glide.with(context).load(R.drawable.toy).fitCenter().into(map_img);
            text_map_flag.setText("경상북도 경산시 압량면 압독2로8길 25-1");
            text_map_call.setText("053-269-7856");
            text_map_open.setText("https://www.instagram.com/petmartdelight/");
            text_map_time.setText("오전 10:00~오후 11:00");
            call_status=true;
            open_status=true;
        }
        else if(maker_name.equals("엄지애견")){
            Glide.with(context).load(R.drawable.toy).fitCenter().into(map_img);
            text_map_flag.setText("대구광역시 동구 신기동 29-1");
            text_map_call.setText("053-963-5653");
            text_map_open.setText("정보가 없습니다");
            text_map_time.setText("정보가 없습니다");
            call_status=true;
        }
        else if(maker_name.equals("똥강아지 대구")){
            Glide.with(context).load(R.drawable.toy).fitCenter().into(map_img);
            text_map_flag.setText("대구광역시 동구 신기동 547-9");
            text_map_call.setText("053-963-5653");
            text_map_open.setText("정보가 없습니다");
            text_map_time.setText("정보가 없습니다");
            call_status=true;
        }
        else if(maker_name.equals("펫마트 동구점")){
            Glide.with(context).load(R.drawable.toy).fitCenter().into(map_img);
            text_map_flag.setText("대구광역시 동구 안심2동 동촌로 403-1");
            text_map_call.setText("053-983-0020");
            text_map_open.setText("http://petmart.co.kr/");
            text_map_time.setText("월~토 : 오전 9:00~오후 10:00\n일 : 오전 10:00~오후 10:00");
            call_status=true;
            open_status=true;
        }
        else if(maker_name.equals("애견의 품격")){
            Glide.with(context).load(R.drawable.toy).fitCenter().into(map_img);
            text_map_flag.setText("대구광역시 수성구 범어3동 국채보상로164길 25");
            text_map_call.setText("053-742-5761");
            text_map_open.setText("https://dignity.modoo.at/");
            text_map_time.setText("정보가 없습니다");
            call_status=true;
            open_status=true;
        }
        else if(maker_name.equals("구피애견")){
            Glide.with(context).load(R.drawable.toy).fitCenter().into(map_img);
            text_map_flag.setText("대구광역시 동구 신암5동 134-328");
            text_map_call.setText("053-951-1013");
            text_map_open.setText("정보가 없습니다");
            text_map_time.setText("정보가 없습니다");
            call_status=true;

        }
        else if(maker_name.equals("미래애견")){
            Glide.with(context).load(R.drawable.toy).fitCenter().into(map_img);
            text_map_flag.setText("대구광역시 북구 대현1동 218-19");
            text_map_call.setText("053-943-0976");
            text_map_open.setText("정보가 없습니다");
            text_map_time.setText("정보가 없습니다");
            call_status=true;

        }
        else if(maker_name.equals("강아지마을 대구")){
            Glide.with(context).load(R.drawable.toy).fitCenter().into(map_img);
            text_map_flag.setText("대구광역시 북구 침산2동 157-8");
            text_map_call.setText("053-427-0607");
            text_map_open.setText("정보가 없습니다");
            text_map_time.setText("정보가 없습니다");
            call_status=true;
        }
        else if(maker_name.equals("펫마트중구점")){
            Glide.with(context).load(R.drawable.toy).fitCenter().into(map_img);
            text_map_flag.setText("대구광역시 중구 성내2동 달구벌대로 2100 메트로센터 지하 E105~108호");
            text_map_call.setText("053-253-1550");
            text_map_open.setText("정보가 없습니다");
            text_map_time.setText("월~토 : 오전 9:00~오후 10:00\n일 : 오전 10:00~오후 10:00");
            call_status=true;
        }
        else if(maker_name.equals("디앤피애견샵")){
            Glide.with(context).load(R.drawable.toy).fitCenter().into(map_img);
            text_map_flag.setText("대구광역시 남구 봉덕1동 572-1");
            text_map_call.setText("053-471-3961");
            text_map_open.setText("정보가 없습니다");
            text_map_time.setText("정보가 없습니다");
            call_status=true;
        }
        else if(maker_name.equals("애견마트 대구")){
            Glide.with(context).load(R.drawable.toy).fitCenter().into(map_img);
            text_map_flag.setText("대구광역시 남구 대명9동 대명로 152");
            text_map_call.setText("053-652-0038");
            text_map_open.setText("정보가 없습니다");
            text_map_time.setText("정보가 없습니다");
            call_status=true;
        }
        else if(maker_name.equals("펫마트서대구점")){
            Glide.with(context).load(R.drawable.toy).fitCenter().into(map_img);
            text_map_flag.setText("대구광역시 남구 대명11동 1116-2");
            text_map_call.setText("053-628-1550");
            text_map_open.setText("정보가 없습니다");
            text_map_time.setText("월~토 : 오전 9:00~오후 10:00\n일 : 오전 10:00~오후 10:00");
            call_status=true;
        }
        else if(maker_name.equals("펫샵 허니몬")){
            Glide.with(context).load(R.drawable.toy).fitCenter().into(map_img);
            text_map_flag.setText("1816 1 층 허니 몬, 달구벌대로 달서구 대구광역시");
            text_map_call.setText("053-624-0723");
            text_map_open.setText("정보가 없습니다");
            text_map_time.setText("오전 10:00~오후 10:00");
            call_status=true;
        }
        else if(maker_name.equals("리틀애견 대구")){
            Glide.with(context).load(R.drawable.toy).fitCenter().into(map_img);
            text_map_flag.setText("대구광역시 서구 비산2.3동 396-8");
            text_map_call.setText("053-565-4858");
            text_map_open.setText("정보가 없습니다");
            text_map_time.setText("정보가 없습니다");
            call_status=true;
        }
        else if(maker_name.equals("펫마트 칠곡점")){
            Glide.with(context).load(R.drawable.toy).fitCenter().into(map_img);
            text_map_flag.setText("대구광역시 북구 태전동 149-3");
            text_map_call.setText("053-326-1550");
            text_map_open.setText("정보가 없습니다");
            text_map_time.setText("월~토 : 오전 9:00~오후 10:00\n일 : 오전 10:00~오후 10:00");
            call_status=true;
        }
        else if(maker_name.equals("펫마트화원점")){
            Glide.with(context).load(R.drawable.toy).fitCenter().into(map_img);
            text_map_flag.setText("대구광역시 달성군 화원읍 설화리 553-10");
            text_map_call.setText("053-635-1550");
            text_map_open.setText("정보가 없습니다");
            text_map_time.setText("월~토 : 오전 9:00~오후 10:00\n일 : 오전 10:00~오후 10:00");
            call_status=true;
        }
        else if(maker_name.equals("더기스토리")){
            Glide.with(context).load(R.drawable.toy).fitCenter().into(map_img);
            text_map_flag.setText("대구광역시 달서구 진천동 293-6");
            text_map_call.setText("053-644-4460");
            text_map_open.setText("정보가 없습니다");
            text_map_time.setText("정보가 없습니다");
            call_status=true;
        }
        else if(maker_name.equals("펫마트 테크노폴리스점")){
            Glide.with(context).load(R.drawable.toy).fitCenter().into(map_img);
            text_map_flag.setText("대구광역시 달성군 현풍면 테크노대로 24");
            text_map_call.setText("053-616-1550");
            text_map_open.setText("정보가 없습니다");
            text_map_time.setText("월~토 : 오전 9:00~오후 10:00\n일 : 오전 10:00~오후 10:00");
            call_status=true;
        }
        else if(maker_name.equals("펫마트밀양점")){
            Glide.with(context).load(R.drawable.toy).fitCenter().into(map_img);
            text_map_flag.setText("경상남도 밀양시 내이동 백민로 43");
            text_map_call.setText("055-354-1552");
            text_map_open.setText("http://www.petmart.kr/");
            text_map_time.setText("월~토 : 오전 9:00~오후 10:00\n일 : 오전 10:00~오후 10:00");
            call_status=true;
        }
        else if(maker_name.equals("이쁜멍멍이")){
            Glide.with(context).load(R.drawable.toy).fitCenter().into(map_img);
            text_map_flag.setText("경상남도 밀양시 내일동 486-1");
            text_map_call.setText("055-354-2752");
            text_map_open.setText("정보가 없습니다");
            text_map_time.setText("정보가 없습니다");
            call_status=true;
        }
        else if(maker_name.equals("도그마미")){
            Glide.with(context).load(R.drawable.toy).fitCenter().into(map_img);
            text_map_flag.setText("부산광역시 금정구 남산동 245-2");
            text_map_call.setText("051-581-0907");
            text_map_open.setText("정보가 없습니다");
            text_map_time.setText("정보가 없습니다");
            call_status=true;
        }
        else if(maker_name.equals("펫마트진해점")){
            Glide.with(context).load(R.drawable.toy).fitCenter().into(map_img);
            text_map_flag.setText("경상남도 창원시 진해구 이동 455-9");
            text_map_call.setText("055-552-1550");
            text_map_open.setText("정보가 없습니다");
            text_map_time.setText("월~토 : 오전 9:00~오후 10:00\n일 : 오전 10:00~오후 10:00");
            call_status=true;
        }
        else if(maker_name.equals("똥개애견용품할인점")){
            Glide.with(context).load(R.drawable.toy).fitCenter().into(map_img);
            text_map_flag.setText("경상남도 창원시 진해구 이동");
            text_map_call.setText("055-541-1001");
            text_map_open.setText("정보가 없습니다");
            text_map_time.setText("정보가 없습니다");
            call_status=true;
        }
        else if(maker_name.equals("장보는강양이마산합포점")){
            Glide.with(context).load(R.drawable.toy).fitCenter().into(map_img);
            text_map_flag.setText("경상남도 창원시 마산합포구 해운동 드림베이대로");
            text_map_call.setText("055-241-4285");
            text_map_open.setText("정보가 없습니다");
            text_map_time.setText("오전 10:00~오후 10:00");
            call_status=true;
        }
        else if(maker_name.equals("언양애견")){
            Glide.with(context).load(R.drawable.toy).fitCenter().into(map_img);
            text_map_flag.setText("울산광역시 울주군 언양읍 동부리 241-3");
            text_map_call.setText("052-264-0025");
            text_map_open.setText("정보가 없습니다");
            text_map_time.setText("정보가 없습니다");
            call_status=true;
        }
        else if(maker_name.equals("개발바닥")){
            Glide.with(context).load(R.drawable.toy).fitCenter().into(map_img);
            text_map_flag.setText("충청남도 아산시 온양1동 1320");
            text_map_call.setText("041-543-9635");
            text_map_open.setText("정보가 없습니다");
            text_map_time.setText("정보가 없습니다");
            call_status=true;
        }
        else if(maker_name.equals("애견마을 아산")){
            Glide.with(context).load(R.drawable.toy).fitCenter().into(map_img);
            text_map_flag.setText("충청남도 아산시 온양2동 201-13");
            text_map_call.setText("041-542-8600");
            text_map_open.setText("정보가 없습니다");
            text_map_time.setText("정보가 없습니다");
            call_status=true;
        }
        else if(maker_name.equals("하얀강아지")){
            Glide.with(context).load(R.drawable.toy).fitCenter().into(map_img);
            text_map_flag.setText("충청남도 아산시 온양1동 80-2");
            text_map_call.setText("041-541-0089");
            text_map_open.setText("정보가 없습니다");
            text_map_time.setText("정보가 없습니다");
            call_status=true;
        }
        else if(maker_name.equals("야옹아멍멍해봐 홍성")){
            Glide.with(context).load(R.drawable.toy).fitCenter().into(map_img);
            text_map_flag.setText("충청남도 홍성군 홍성읍 오관리 322-1");
            text_map_call.setText("041-632-8228");
            text_map_open.setText("정보가 없습니다");
            text_map_time.setText("오전 8:00~오후 10:00");
            call_status=true;
        }
        else if(maker_name.equals("야옹아멍멍해봐 서산")){
            Glide.with(context).load(R.drawable.toy).fitCenter().into(map_img);
            text_map_flag.setText("충청남도 서산시 동문1동 201-1");
            text_map_call.setText("041-664-0049");
            text_map_open.setText("https://ymss.modoo.at/");
            text_map_time.setText("오전 10:00~오후 10:00");
            call_status=true;
            open_status=true;
        }
        else if(maker_name.equals("애견용품할인매장 서산")){
            Glide.with(context).load(R.drawable.toy).fitCenter().into(map_img);
            text_map_flag.setText("충청남도 서산시 동문동 1025-32");
            text_map_call.setText("041-669-5979");
            text_map_open.setText("정보가 없습니다");
            text_map_time.setText("정보가 없습니다");
            call_status=true;
        }
        else if(maker_name.equals("서산애견")){
            Glide.with(context).load(R.drawable.toy).fitCenter().into(map_img);
            text_map_flag.setText("충청남도 서산시 읍내동 23-13");
            text_map_call.setText("041-667-6266");
            text_map_open.setText("정보가 없습니다");
            text_map_time.setText("정보가 없습니다");
            call_status=true;
        }
        else if(maker_name.equals("애견사랑 서산")){
            Glide.with(context).load(R.drawable.toy).fitCenter().into(map_img);
            text_map_flag.setText("충청남도 서산시 읍내동 124-19");
            text_map_call.setText("041-668-4507");
            text_map_open.setText("정보가 없습니다");
            text_map_time.setText("정보가 없습니다");
            call_status=true;
        }
        else if(maker_name.equals("금비동물애견")){
            Glide.with(context).load(R.drawable.toy).fitCenter().into(map_img);
            text_map_flag.setText("충청남도 공주시 신관동 256-6");
            text_map_call.setText("041-856-8083");
            text_map_open.setText("정보가 없습니다");
            text_map_time.setText("정보가 없습니다");
            call_status=true;
        }
        else if(maker_name.equals("애견클럽 공주")){
            Glide.with(context).load(R.drawable.toy).fitCenter().into(map_img);
            text_map_flag.setText("충청남도 공주시 신관동 257-6");
            text_map_call.setText("041-856-3379");
            text_map_open.setText("정보가 없습니다");
            text_map_time.setText("정보가 없습니다");
            call_status=true;
        }
        else if(maker_name.equals("빅토리애견")){
            Glide.with(context).load(R.drawable.toy).fitCenter().into(map_img);
            text_map_flag.setText("경상북도 문경시 점촌동 178-3");
            text_map_call.setText("054-552-8235");
            text_map_open.setText("정보가 없습니다");
            text_map_time.setText("정보가 없습니다");
            call_status=true;
        }
        else if(maker_name.equals("퍼피엔젤애견미용전문샵")){
            Glide.with(context).load(R.drawable.toy).fitCenter().into(map_img);
            text_map_flag.setText("경상북도 상주시 복룡동 230-11");
            text_map_call.setText("054-536-0021");
            text_map_open.setText("정보가 없습니다");
            text_map_time.setText("정보가 없습니다");
            call_status=true;
        }
        else if(maker_name.equals("김천애견클럽")){
            Glide.with(context).load(R.drawable.toy).fitCenter().into(map_img);
            text_map_flag.setText("경상북도 김천시 부곡동 428-21");
            text_map_call.setText("054-434-1472");
            text_map_open.setText("정보가 없습니다");
            text_map_time.setText("정보가 없습니다");
            call_status=true;
        }
        else if(maker_name.equals("펫마트김천점")){
            Glide.with(context).load(R.drawable.toy).fitCenter().into(map_img);
            text_map_flag.setText("경상북도 김천시 성내동 74-1");
            text_map_call.setText("054-434-1550");
            text_map_open.setText("http://www.petmart.kr/");
            text_map_time.setText("월~토 : 오전 9:00~오후 10:00\n일 : 오전 10:00~오후 10:00");
            call_status=true;
        }
        else if(maker_name.equals("애견천국 구미")){
            Glide.with(context).load(R.drawable.toy).fitCenter().into(map_img);
            text_map_flag.setText("경상북도 구미시 형곡동 152-3");
            text_map_call.setText("054-444-6456");
            text_map_open.setText("정보가 없습니다");
            text_map_time.setText("정보가 없습니다");
            call_status=true;
        }
        else if(maker_name.equals("애견용품백화점")){
            Glide.with(context).load(R.drawable.toy).fitCenter().into(map_img);
            text_map_flag.setText("경상북도 구미시 원평1동 964-209");
            text_map_call.setText("054-453-9460");
            text_map_open.setText("정보가 없습니다");
            text_map_time.setText("정보가 없습니다");
            call_status=true;
        }
        else if(maker_name.equals("펫마트 구미점")){
            Glide.with(context).load(R.drawable.toy).fitCenter().into(map_img);
            text_map_flag.setText("경상북도 구미시 원평동 1050-3");
            text_map_call.setText("054-444-1550");
            text_map_open.setText("정보가 없습니다");
            text_map_time.setText("월~토 : 오전 9:00~오후 10:00\n일 : 오전 10:00~오후 10:00");
            call_status=true;
        }
        else if(maker_name.equals("바람난똥강아지")){
            Glide.with(context).load(R.drawable.toy).fitCenter().into(map_img);
            text_map_flag.setText("경상북도 구미시 옥계동 790-2");
            text_map_call.setText("054-476-4527");
            text_map_open.setText("정보가 없습니다");
            text_map_time.setText("정보가 없습니다");
            call_status=true;
        }
        else if(maker_name.equals("애견나라 칠곡")){
            Glide.with(context).load(R.drawable.toy).fitCenter().into(map_img);
            text_map_flag.setText("경상북도 칠곡군 왜관읍 왜관리 251-2");
            text_map_call.setText("054-973-5654");
            text_map_open.setText("정보가 없습니다");
            text_map_time.setText("정보가 없습니다");
            call_status=true;
        }
        else if(maker_name.equals("펫마트 왜관점")){
            Glide.with(context).load(R.drawable.toy).fitCenter().into(map_img);
            text_map_flag.setText("경상북도 칠곡군 왜관읍 중앙로 67");
            text_map_call.setText("054-971-1550");
            text_map_open.setText("http://petmart.kr/");
            text_map_time.setText("월~토 : 오전 9:00~오후 10:00\n일 : 오전 10:00~오후 10:00");
            call_status=true;
        }
        else if(maker_name.equals("아림애견의집")){
            Glide.with(context).load(R.drawable.toy).fitCenter().into(map_img);
            text_map_flag.setText("경상남도 거창군 거창읍 중앙리 334-2");
            text_map_call.setText("055-944-0233");
            text_map_open.setText("정보가 없습니다");
            text_map_time.setText("정보가 없습니다");
            call_status=true;
        }
        else if(maker_name.equals("강아지풀")){
            Glide.with(context).load(R.drawable.toy).fitCenter().into(map_img);
            text_map_flag.setText("경상남도 거창군 거창읍 상림리 814-2");
            text_map_call.setText("055-942-5691");
            text_map_open.setText("정보가 없습니다");
            text_map_time.setText("정보가 없습니다");
            call_status=true;
        }
        else if(maker_name.equals("강아지멋내기")){
            Glide.with(context).load(R.drawable.toy).fitCenter().into(map_img);
            text_map_flag.setText("강원도 원주시 문막읍 동화리 1264");
            text_map_call.setText("033-735-0045");
            text_map_open.setText("정보가 없습니다");
            text_map_time.setText("정보가 없습니다");
            call_status=true;
        }
        else if(maker_name.equals("둘리애견")){
            Glide.with(context).load(R.drawable.toy).fitCenter().into(map_img);
            text_map_flag.setText("강원도 원주시 관설동 1747-4");
            text_map_call.setText("033-766-0556");
            text_map_open.setText("정보가 없습니다");
            text_map_time.setText("정보가 없습니다");
            call_status=true;
        }
        else if(maker_name.equals("애니펫살롱")){
            Glide.with(context).load(R.drawable.toy).fitCenter().into(map_img);
            text_map_flag.setText("강원도 원주시 단구동 1532-16");
            text_map_call.setText("033-762-0671");
            text_map_open.setText("정보가 없습니다");
            text_map_time.setText("정보가 없습니다");
            call_status=true;
        }
        else if(maker_name.equals("강이랑아지랑(단계점)")){
            Glide.with(context).load(R.drawable.toy).fitCenter().into(map_img);
            text_map_flag.setText("강원도 원주시 단계동 915");
            text_map_call.setText("033-742-3288");
            text_map_open.setText("정보가 없습니다");
            text_map_time.setText("정보가 없습니다");
            call_status=true;
        }

        // 공원
        else if(maker_name.equals("컨츄리독힐링파크")){
            Glide.with(context).load(R.drawable.park_1).fitCenter().into(map_img);
            text_map_flag.setText("경기도 용인시 처인구 모현면 갈담리 614");
            text_map_call.setText("031-333-2633");
            text_map_open.setText("https://blog.naver.com/ilovepetsu");
            text_map_time.setText("정보가 없습니다");
            open_status = true;
            call_status = true;
        }
        else if(maker_name.equals("기흥레스피아호수공원")){
            Glide.with(context).load(R.drawable.park_1).fitCenter().into(map_img);
            text_map_flag.setText("경기도 용인시 기흥구 하갈동 114-5");
            text_map_call.setText("정보가 없습니다");
            text_map_open.setText("24시간 영업");
            text_map_time.setText("정보가 없습니다");
        }
        else if(maker_name.equals("레이지독")){
            Glide.with(context).load(R.drawable.park_1).fitCenter().into(map_img);
            text_map_flag.setText("경기도 광주시 도척면 유정리 119-1");
            text_map_call.setText("010-6777-5606");
            text_map_open.setText("https://blog.naver.com/lazy-dog");
            text_map_time.setText("오전 11:00~오후 7:00(수요일, 목요일 휴무)");
            open_status = true;
            call_status = true;
        }
        else if(maker_name.equals("인천개공원")){
            Glide.with(context).load(R.drawable.park_1).fitCenter().into(map_img);
            text_map_flag.setText("인천광역시 남동구 장수서창동 무네미로 201-15");
            text_map_call.setText("032-461-6021");
            text_map_open.setText("https://happy-namdong.tistory.com/5592");
            text_map_time.setText("오전 11:00~오후 9:00");
            open_status = true;
            call_status = true;
        }
        else if(maker_name.equals("삼막애견공원")){
            Glide.with(context).load(R.drawable.park_1).fitCenter().into(map_img);
            text_map_flag.setText("경기도 안양시 만안구 석수동 14-5");
            text_map_call.setText("032-461-6021");
            text_map_time.setText("오전 10시 ~ 오후 7시(월요일 휴무)");
            text_map_open.setText("정보가 없습니다");
            call_status = true;
        }
        else if(maker_name.equals("퍼피앤커피")){
            Glide.with(context).load(R.drawable.park_1).fitCenter().into(map_img);
            text_map_flag.setText("서울특별시 관악구 서원동 1637-18");
            text_map_call.setText("02-883-1143");
            text_map_time.setText("12:00 ~ 22:30");
            text_map_open.setText("정보가 없습니다");
            call_status = true;
        }
        else if(maker_name.equals("도그파크")){
            Glide.with(context).load(R.drawable.park_1).fitCenter().into(map_img);
            text_map_flag.setText("경기도 용인시 처인구 모현면 오산리 372");
            text_map_call.setText("02-883-1143");
            text_map_time.setText("오전 10:00~오후 8:00");
            text_map_open.setText("정보가 없습니다");
            call_status = true;
        }
        else if(maker_name.equals("Dog Park Anjeong-ri")){
            Glide.with(context).load(R.drawable.park_1).fitCenter().into(map_img);
            text_map_flag.setText("경기도 평택시 팽성읍 송화리 66-3");
            text_map_call.setText("정보가 없습니다");
            text_map_open.setText("정보가 없습니다");
            text_map_time.setText("정보가 없습니다");
        }
        else if(maker_name.equals("Eden Dog Park")){
            Glide.with(context).load(R.drawable.park_1).fitCenter().into(map_img);
            text_map_flag.setText("충청남도 아산시 도고면 온천대로 605-23");
            text_map_call.setText("정보가 없습니다");
            text_map_open.setText("정보가 없습니다");
            text_map_time.setText("정보가 없습니다");
        }
        else if(maker_name.equals("Dog park")){
            Glide.with(context).load(R.drawable.park_1).fitCenter().into(map_img);
            text_map_flag.setText("충청남도 천안시 동남구 신부동");
            text_map_call.setText("정보가 없습니다");
            text_map_open.setText("정보가 없습니다");
            text_map_time.setText("정보가 없습니다");
        }
        else if(maker_name.equals("오송호수공원")){
            Glide.with(context).load(R.drawable.park_1).fitCenter().into(map_img);
            text_map_flag.setText("충청북도 청주시 청원구 오송읍 만수리");
            text_map_call.setText("정보가 없습니다");
            text_map_time.setText("24시간 영업");
            text_map_open.setText("정보가 없습니다");
        }
        else if(maker_name.equals("준스켄넬")){
            Glide.with(context).load(R.drawable.park_1).fitCenter().into(map_img);
            text_map_flag.setText("충청북도 청주시 상당구 용담동 100-1");
            text_map_call.setText("정보가 없습니다");
            text_map_open.setText("정보가 없습니다");
            text_map_time.setText("정보가 없습니다");
        }
        else if(maker_name.equals("홍동애향공원")){
            Glide.with(context).load(R.drawable.park_1).fitCenter().into(map_img);
            text_map_flag.setText("충청남도 홍성군 홍동면 구정리 519");
            text_map_call.setText("정보가 없습니다");
            text_map_time.setText("정보가 없습니다.");
            text_map_open.setText("정보가 없습니다");
        }
        else if(maker_name.equals("보라매공원")){
            Glide.with(context).load(R.drawable.park_1).fitCenter().into(map_img);
            text_map_flag.setText("서울특별시 동작구 신대방2동 여의대방로20길 33");
            text_map_call.setText("02-2181-1190");
            text_map_time.setText("24시간 영업");
            text_map_open.setText("http://parks.seoul.go.kr/template/sub/boramae.do");
            open_status = true;
            call_status = true;
        }
        else if(maker_name.equals("루디힐")){
            Glide.with(context).load(R.drawable.park_1).fitCenter().into(map_img);
            text_map_flag.setText("경기도 양평군 서종면 수입리 175-10");
            text_map_call.setText("010-4563-5383");
            text_map_time.setText("오전 9:00~오후 9:00(수요일 휴무)");
            text_map_open.setText("정보가 없습니다");
            call_status = true;
        }
        else if(maker_name.equals("신나물 두매향기")){
            Glide.with(context).load(R.drawable.park_1).fitCenter().into(map_img);
            text_map_flag.setText("경기도 양평군 양서면 목왕로592번길 62-59");
            text_map_call.setText("031-774-3114");
            text_map_time.setText("오전 9:00~오후 9:00");
            text_map_open.setText("정보가 없습니다");
            call_status = true;
        }
        else if(maker_name.equals("아우름")){
            Glide.with(context).load(R.drawable.park_1).fitCenter().into(map_img);
            text_map_flag.setText("경기도 남양주시 와부읍 월문리 53-1");
            text_map_call.setText("정보가 없습니다");
            text_map_time.setText("정보가 없습니다");
            text_map_open.setText("정보가 없습니다");
        }
        else if(maker_name.equals("애완견돌봄원")){
            Glide.with(context).load(R.drawable.park_1).fitCenter().into(map_img);
            text_map_flag.setText("경기도 남양주시 진건읍 사릉로 720-135");
            text_map_call.setText("정보가 없습니다");
            text_map_time.setText("정보가 없습니다");
            text_map_open.setText("https://dolbomwon.modoo.at/");
            open_status = true;
        }
        else if(maker_name.equals("광교호수공원 애견놀이터")){
            Glide.with(context).load(R.drawable.park_1).fitCenter().into(map_img);
            text_map_flag.setText("경기도 수원시 영통구 하동 40-1");
            text_map_call.setText("정보가 없습니다");
            text_map_time.setText("정보가 없습니다");
            text_map_open.setText("정보가 없습니다");
        }
        else if(maker_name.equals("이웅종강아지테마파크")){
            Glide.with(context).load(R.drawable.park_1).fitCenter().into(map_img);
            text_map_flag.setText("경기도 고양시 일산서구 송포동 한류월드로 300");
            text_map_call.setText("정보가 없습니다");
            text_map_time.setText("031-961-6450");
            text_map_open.setText("정보가 없습니다");
            call_status = true;
        }
        else if(maker_name.equals("초안산공공반려견놀이터")){
            Glide.with(context).load(R.drawable.park_1).fitCenter().into(map_img);
            text_map_flag.setText("서울특별시 도봉구 창동 산24");
            text_map_call.setText("정보가 없습니다");
            text_map_time.setText("정보가 없습니다");
            text_map_open.setText("정보가 없습니다");
        }
        else if(maker_name.equals("부산펫택시")){
            Glide.with(context).load(R.drawable.park_1).fitCenter().into(map_img);
            text_map_flag.setText("부산광역시 남구 대연동 수영로 295");
            text_map_call.setText("010-9791-5001");
            text_map_time.setText("24시간 영업");
            text_map_open.setText("https://busanpettaxi.modoo.at/");
            open_status = true;
            call_status = true;
        }
        else if(maker_name.equals("애견운동공원")){
            Glide.with(context).load(R.drawable.park_1).fitCenter().into(map_img);
            text_map_flag.setText("울산광역시 남구 옥동");
            text_map_call.setText("052-256-6389");
            text_map_time.setText("정보가 없습니다");
            text_map_open.setText("정보가 없습니다");
            call_status = true;
        }
        else if(maker_name.equals("독봉산웰빙공원")){
            Glide.with(context).load(R.drawable.park_1).fitCenter().into(map_img);
            text_map_flag.setText("경상남도 거제시 상동동 373-1");
            text_map_call.setText("정보가 없습니다");
            text_map_time.setText("정보가 없습니다");
            text_map_open.setText("정보가 없습니다");
        }
        else if(maker_name.equals("뿌리공원")){
            Glide.with(context).load(R.drawable.park_1).fitCenter().into(map_img);
            text_map_flag.setText("대전광역시 중구 침산동 뿌리공원로 79");
            text_map_call.setText("042-288-8310");
            text_map_time.setText("오전 6:00~오후 9:00");
            text_map_open.setText("정보가 없습니다");
            call_status = true;
        }
        else if(maker_name.equals("작은내수변공원")){
            Glide.with(context).load(R.drawable.park_1).fitCenter().into(map_img);
            text_map_flag.setText("대전광역시 유성구 봉명동");
            text_map_call.setText("정보가 없습니다");
            text_map_time.setText("24시간 영업");
            text_map_open.setText("정보가 없습니다");
        }
        else if(maker_name.equals("보라매공원 대전")){
            Glide.with(context).load(R.drawable.park_1).fitCenter().into(map_img);
            text_map_flag.setText("대전광역시 서구 둔산동");
            text_map_call.setText("정보가 없습니다");
            text_map_time.setText("24시간 영업");
            text_map_open.setText("정보가 없습니다");
        }
        else if(maker_name.equals("대동하늘공원")){
            Glide.with(context).load(R.drawable.park_1).fitCenter().into(map_img);
            text_map_flag.setText("대전광역시 동구 자양동 20-9");
            text_map_call.setText("정보가 없습니다");
            text_map_time.setText("24시간 영업");
            text_map_open.setText("정보가 없습니다");
        }
        else if(maker_name.equals("동락공원")){
            Glide.with(context).load(R.drawable.park_1).fitCenter().into(map_img);
            text_map_flag.setText("경상북도 구미시 진평동 766");
            text_map_call.setText("054-473-5202");
            text_map_time.setText("24시간 영업");
            text_map_open.setText("정보가 없습니다");
            call_status = true;
        }
        else if(maker_name.equals("침산공원")){
            Glide.with(context).load(R.drawable.park_1).fitCenter().into(map_img);
            text_map_flag.setText("대구광역시 북구 침산동 1168-3");
            text_map_call.setText("053-665-4131");
            text_map_time.setText("24시간 영업");
            text_map_open.setText("정보가 없습니다");
            call_status = true;
        }
        else if(maker_name.equals("하얀민들레")){
            Glide.with(context).load(R.drawable.park_1).fitCenter().into(map_img);
            text_map_flag.setText("경상북도 청도군 화양읍 852 남성현로");
            text_map_call.setText("1599-1627");
            text_map_time.setText("24시간 영업");
            text_map_open.setText("http://youngheal.com/board.php?board=freeboard&menu=index");
            open_status = true;
            call_status = true;
        }
        else if(maker_name.equals("복현공원")){
            Glide.with(context).load(R.drawable.park_1).fitCenter().into(map_img);
            text_map_flag.setText("대구광역시 북구 복현동");
            text_map_call.setText("정보가 없습니다");
            text_map_time.setText("정보가 없습니다");
            text_map_open.setText("정보가 없습니다");
        }
        else if(maker_name.equals("진도개테마파크")){
            Glide.with(context).load(R.drawable.park_1).fitCenter().into(map_img);
            text_map_flag.setText("전라남도 진도군 진도읍 동외리 57");
            text_map_call.setText("061-540-6308");
            text_map_time.setText("오전 9:00~오후 6:00");
            text_map_open.setText("http://www.jindo.go.kr/tour/sub.cs?m=14");
            open_status = true;
            call_status = true;
        }
        else if(maker_name.equals("모현공원")){
            Glide.with(context).load(R.drawable.park_1).fitCenter().into(map_img);
            text_map_flag.setText("전라북도 익산시 모현동1가");
            text_map_call.setText("정보가 없습니다");
            text_map_time.setText("정보가 없습니다");
            text_map_open.setText("정보가 없습니다");
        }
        else if(maker_name.equals("용인중앙공원")){
            Glide.with(context).load(R.drawable.park_1).fitCenter().into(map_img);
            text_map_flag.setText("경기도 용인시 처인구 김량장동");
            text_map_call.setText("정보가 없습니다");
            text_map_time.setText("24시간 영업");
            text_map_open.setText("정보가 없습니다");
        }
        else if(maker_name.equals("동백호수공원")){
            Glide.with(context).load(R.drawable.park_1).fitCenter().into(map_img);
            text_map_flag.setText("경기도 용인시 기흥구 중동 동백5로 12");
            text_map_call.setText("정보가 없습니다");
            text_map_time.setText("24시간 영업");
            text_map_open.setText("정보가 없습니다");
        }
        else if(maker_name.equals("한길찬공원")){
            Glide.with(context).load(R.drawable.park_1).fitCenter().into(map_img);
            text_map_flag.setText("경기도 용인시 기흥구 중동");
            text_map_call.setText("정보가 없습니다");
            text_map_time.setText("24시간 영업");
            text_map_open.setText("정보가 없습니다");
        }

        //애견카페
        else if(maker_name.equals("애견카페 에코독")){
            Glide.with(context).load(R.drawable.cafe_1).fitCenter().into(map_img);
            text_map_flag.setText("경기도 용인시 기흥구 중동");
            text_map_call.setText("031-206-7070");
            text_map_time.setText("월~토 : 오전 11:00~오후 10:00(일 휴무)");
            text_map_open.setText("정보가 없습니다");
            call_status = true;
        }
        else if(maker_name.equals("아이러브퍼피")){
            Glide.with(context).load(R.drawable.cafe_1).fitCenter().into(map_img);
            text_map_flag.setText("경기도 용인시 수지구 죽전동 876-6");
            text_map_call.setText("031-261-0969");
            text_map_time.setText("오후 12:30~10:00");
            text_map_open.setText("정보가 없습니다");
            call_status = true;
        }
        else if(maker_name.equals("멍스데이")){
            Glide.with(context).load(R.drawable.cafe_1).fitCenter().into(map_img);
            text_map_flag.setText("경기도 수원시 영통구 이의동 27-26");
            text_map_call.setText("정보가 없습니다");
            text_map_time.setText("정보가 없습니다");
            text_map_open.setText("정보가 없습니다");

        }
        else if(maker_name.equals("딩고 영통구")){
            Glide.with(context).load(R.drawable.cafe_1).fitCenter().into(map_img);
            text_map_flag.setText("경기도 수원시 영통구 망포동 599-1");
            text_map_call.setText("070-4242-5959");
            text_map_time.setText("화~일 : 오전 10:00~오후 9:00\n월 : 오전 10:00~오전 12:00");
            text_map_open.setText("https://www.instagram.com/dogcafedingo/");
            call_status = true;
            open_status = true;

        }
        else if(maker_name.equals("플레이어스")){
            Glide.with(context).load(R.drawable.cafe_1).fitCenter().into(map_img);
            text_map_flag.setText("경기도 화성시 동탄2동 123-3 1층");
            text_map_call.setText("070-8968-8860");
            text_map_time.setText("오전 11:00~오후 12:00");
            text_map_open.setText("정보가 없습니다");
            call_status = true;

        }
        else if(maker_name.equals("카페멍 안양")){
            Glide.with(context).load(R.drawable.cafe_1).fitCenter().into(map_img);
            text_map_flag.setText("경기도 안양시 만안구 안양동 장내로139번길 56-5");
            text_map_call.setText("031-441-7708");
            text_map_time.setText("오후 12:00~10:00");
            text_map_open.setText("https://cafemung.modoo.at/");
            call_status = true;
            open_status = true;

        }
        else if(maker_name.equals("해피치 애견카페")){
            Glide.with(context).load(R.drawable.cafe_1).fitCenter().into(map_img);
            text_map_flag.setText("서울특별시 금천구 독산1동 시흥대로 351");
            text_map_call.setText("010-7338-5315");
            text_map_time.setText("정보가 없습니다");
            text_map_open.setText("정보가 없습니다");
            call_status = true;

        }
        else if(maker_name.equals("제이독(J.DOG)애견카페")){
            Glide.with(context).load(R.drawable.cafe_1).fitCenter().into(map_img);
            text_map_flag.setText("경기도 평택시 용이동 126-7");
            text_map_call.setText("031-654-0690");
            text_map_time.setText("정보가 없습니다");
            text_map_open.setText("정보가 없습니다");
            call_status = true;

        }
        else if(maker_name.equals("청주애견cafe D.D")){
            Glide.with(context).load(R.drawable.cafe_1).fitCenter().into(map_img);
            text_map_flag.setText("충청북도 청주시 상당구 우암동 345");
            text_map_call.setText("043-225-0221");
            text_map_time.setText("정보가 없습니다");
            text_map_open.setText("http://www.wasc.or.kr/default/business/biz05.php");
            call_status = true;
            open_status = true;

        }
        else if(maker_name.equals("헤이독 청주")){
            Glide.with(context).load(R.drawable.cafe_1).fitCenter().into(map_img);
            text_map_flag.setText("충청북도 청주시 상당구 남문로2가 성안로 50");
            text_map_call.setText("043-221-7380");
            text_map_time.setText("오전 11:00~오후 10:00");
            text_map_open.setText("정보가 없습니다");
            call_status = true;

        }
        else if(maker_name.equals("코이이누")){
            Glide.with(context).load(R.drawable.cafe_1).fitCenter().into(map_img);
            text_map_flag.setText("대전광역시 서구 둔산동 1067번지 5층");
            text_map_call.setText("042-545-8886");
            text_map_time.setText("정보가 없습니다");
            text_map_open.setText("정보가 없습니다");
            call_status = true;

        }
        else if(maker_name.equals("원쿡 대구")){
            Glide.with(context).load(R.drawable.cafe_1).fitCenter().into(map_img);
            text_map_flag.setText("대흥동 138-4번지 수성구 대구광역시 KR");
            text_map_call.setText("053-792-1060");
            text_map_time.setText("정보가 없습니다");
            text_map_open.setText("정보가 없습니다");
            call_status = true;

        }
        else if(maker_name.equals("도기온애견카페")){
            Glide.with(context).load(R.drawable.cafe_1).fitCenter().into(map_img);
            text_map_flag.setText("경상북도 경산시 하양읍 청천리 550");
            text_map_call.setText("정보가 없습니다");
            text_map_time.setText("정보가 없습니다");
            text_map_open.setText("정보가 없습니다");

        }
        else if(maker_name.equals("라떼애견카페 (개스트하우스)")){
            Glide.with(context).load(R.drawable.cafe_1).fitCenter().into(map_img);
            text_map_flag.setText("울산광역시 울주군 상북면 삽재로 310-1");
            text_map_call.setText("052-264-0255");
            text_map_time.setText("정보가 없습니다");
            text_map_open.setText("정보가 없습니다");

            call_status = true;

        }
        else if(maker_name.equals("덕수네 애견카페")){
            Glide.with(context).load(R.drawable.cafe_1).fitCenter().into(map_img);
            text_map_flag.setText("부산광역시 부산진구 전포동 동천로 66");
            text_map_call.setText("051-804-2956");
            text_map_time.setText("오전 11:30~오후 10:30");
            text_map_open.setText("정보가 없습니다");

            call_status = true;

        }
        else if(maker_name.equals("포레스트 3002")){
            Glide.with(context).load(R.drawable.cafe_1).fitCenter().into(map_img);
            text_map_flag.setText("부산광역시 강서구 녹산동 낙동남로682번길 262");
            text_map_call.setText("051-941-7893");
            text_map_time.setText("수~월 : 오전 10:30~오후 9:30(화 휴무)");
            text_map_open.setText("https://www.instagram.com/forest3002/");
            open_status = true;
            call_status = true;

        }
        else if(maker_name.equals("빈티지399")){
            Glide.with(context).load(R.drawable.cafe_1).fitCenter().into(map_img);
            text_map_flag.setText("광주광역시 광산구 평동 399-3");
            text_map_call.setText("062-943-0668");
            text_map_time.setText("정보가 없습니다");
            text_map_open.setText("정보가 없습니다");

            call_status = true;

        }
        else if(maker_name.equals("나비야놀자")){
            Glide.with(context).load(R.drawable.cafe_1).fitCenter().into(map_img);
            text_map_flag.setText("경상북도 칠곡군 석적읍 남구미로 191");
            text_map_call.setText("010-8803-7494");
            text_map_time.setText("금,월,수 : 오전 9:00~오후 7:00");
            text_map_open.setText("정보가 없습니다");

            call_status = true;

        }
        else if(maker_name.equals("디반식스")){
            Glide.with(context).load(R.drawable.cafe_1).fitCenter().into(map_img);
            text_map_flag.setText("경상남도 창원시 의창구 신월동 상남로248번길 4");
            text_map_call.setText("050-71314-3952");
            text_map_time.setText("오전 11:00~오후 10:00");
            text_map_open.setText("정보가 없습니다");

            call_status = true;

        }
        else if(maker_name.equals("멍뭉애견카페")){
            Glide.with(context).load(R.drawable.cafe_1).fitCenter().into(map_img);
            text_map_flag.setText("대구광역시 달서구 두류3동 495-17");
            text_map_call.setText("070-4098-5789");
            text_map_time.setText("정보가 없습니다");
            text_map_open.setText("정보가 없습니다");

            call_status = true;

        }
        else if(maker_name.equals("퍼니퍼피")){
            Glide.with(context).load(R.drawable.cafe_1).fitCenter().into(map_img);
            text_map_flag.setText("경상북도 포항시 북구 장량동 양덕남로 85");
            text_map_call.setText("054-242-8287");
            text_map_time.setText("정보가 없습니다");
            text_map_open.setText("http://funny-puppy.com/");
            open_status = true;
            call_status = true;
        }
        else if(maker_name.equals("모모몽애견(카페)")){
            Glide.with(context).load(R.drawable.cafe_1).fitCenter().into(map_img);
            text_map_flag.setText("강원도 동해시 천곡동 1061");
            text_map_call.setText("033-534-1979");
            text_map_time.setText("정보가 없습니다");
            text_map_open.setText("정보가 없습니다");
            call_status = true;
        }
        else if(maker_name.equals("카페히릿")){
            Glide.with(context).load(R.drawable.cafe_1).fitCenter().into(map_img);
            text_map_flag.setText("화양동 111-72번지 3층 2호 광진구 서울특별시 KR");
            text_map_call.setText("070-8870-4442");
            text_map_time.setText("정보가 없습니다");
            text_map_open.setText("정보가 없습니다");
            call_status = true;
        }
        else if(maker_name.equals("애견 카페 개그멍")){
            Glide.with(context).load(R.drawable.cafe_1).fitCenter().into(map_img);
            text_map_flag.setText("서울특별시 노원구 상계2동 상계로1길 8");
            text_map_call.setText("02-931-5275");
            text_map_time.setText("오후 1:00~10:00(화 휴무)");
            text_map_open.setText("https://cafe.naver.com/gagmung");
            call_status = true;
            open_status=true;
        }
        else if(maker_name.equals("(주)펫톡")){
            Glide.with(context).load(R.drawable.cafe_1).fitCenter().into(map_img);
            text_map_flag.setText("인천광역시 부평구 부평동 185-24");
            text_map_call.setText("032-433-8488");
            text_map_time.setText("정보가 없습니다");
            text_map_open.setText("정보가 없습니다");
            call_status = true;
        }
        else if(maker_name.equals("아루스(애견카페)")){
            Glide.with(context).load(R.drawable.cafe_1).fitCenter().into(map_img);
            text_map_flag.setText("경기도 화성시 매송면 화성로 2298-8");
            text_map_call.setText("정보가 없습니다");
            text_map_time.setText("오전 11:00~오후 9:00(목 휴무)");
            text_map_open.setText("정보가 없습니다");
        }
        else if(maker_name.equals("개간지애견카페")){
            Glide.with(context).load(R.drawable.cafe_1).fitCenter().into(map_img);
            text_map_flag.setText("단원구 선부동 1070-11번지 304호 동양빌딩 안산시 경기도 KR");
            text_map_call.setText("031-401-5570");
            text_map_time.setText("정보가 없습니다");
            text_map_open.setText("정보가 없습니다");
        }
        else if(maker_name.equals("개버랜드")){
            Glide.with(context).load(R.drawable.cafe_1).fitCenter().into(map_img);
            text_map_flag.setText("경기도 용인시 처인구 마평동 457-1");
            text_map_call.setText("정보가 없습니다");
            text_map_time.setText("정보가 없습니다");
            text_map_open.setText("정보가 없습니다");
        }
        else if(maker_name.equals("하이디 펫")){
            Glide.with(context).load(R.drawable.cafe_1).fitCenter().into(map_img);
            text_map_flag.setText("경기도 화성시 동탄면 중리 435");
            text_map_call.setText("정보가 없습니다");
            text_map_time.setText("정보가 없습니다");
            text_map_open.setText("정보가 없습니다");
        }
        else if(maker_name.equals("개스타그램 (Dog Cafe)")){
            Glide.with(context).load(R.drawable.cafe_1).fitCenter().into(map_img);
            text_map_flag.setText("경기도 평택시 신장동 223-8");
            text_map_call.setText("정보가 없습니다");
            text_map_time.setText("오전 11:00~오후 9:00");
            text_map_open.setText("정보가 없습니다");
        }
        else if(maker_name.equals("Pet Story 카페")){
            Glide.with(context).load(R.drawable.cafe_1).fitCenter().into(map_img);
            text_map_flag.setText("경기도 평택시 고덕면 두릉리 296-6");
            text_map_call.setText("070-4117-5959");
            text_map_time.setText("오전 10:00~오후 9:00");
            text_map_open.setText("https://www.instagram.com/petstorycafe/");
        }
        else if(maker_name.equals("A dog house 개집")){
            Glide.with(context).load(R.drawable.cafe_1).fitCenter().into(map_img);
            text_map_flag.setText("경기도 평택시 독곡동 308-2 2층");
            text_map_call.setText("정보가 없습니다");
            text_map_time.setText("정보가 없습니다");
            text_map_open.setText("정보가 없습니다");
        }
        else if(maker_name.equals("BARACHEL (바라겔)")){
            Glide.with(context).load(R.drawable.cafe_1).fitCenter().into(map_img);
            text_map_flag.setText("충청남도 서산시 음암면 도당꽃밭재길 59-57");
            text_map_call.setText("041-663-1312");
            text_map_time.setText("오전 10:00~오후 10:00");
            text_map_open.setText("정보가 없습니다");
            call_status = true;
        }
        else if(maker_name.equals("원독")){
            Glide.with(context).load(R.drawable.cafe_1).fitCenter().into(map_img);
            text_map_flag.setText("충청남도 연기군 남면 고정리 701-1 고정리");
            text_map_call.setText("044-867-0977");
            text_map_time.setText("오전 10:00~오후 10:00");
            text_map_open.setText("http://www.onedog.co.kr/");
            call_status = true;
            open_status = true;
        }
        else if(maker_name.equals("INUINN")){
            Glide.with(context).load(R.drawable.cafe_1).fitCenter().into(map_img);
            text_map_flag.setText("대전광역시 유성구 방동 455-2");
            text_map_call.setText("010-4725-4111");
            text_map_time.setText("오전 11:30~오후 8:30(월 휴무)");
            text_map_open.setText("https://inuinn.business.site/?utm_source=gmb&utm_medium=referral");
            call_status = true;
            open_status = true;
        }
        else if(maker_name.equals("핫도그애견카페")){
            Glide.with(context).load(R.drawable.cafe_1).fitCenter().into(map_img);
            text_map_flag.setText("대전광역시 대덕구 송촌동 449-4");
            text_map_call.setText("042-626-2987");
            text_map_time.setText("정보가 없습니다");
            text_map_open.setText("정보가 없습니다");
            call_status = true;
        }

        //애견호텔
        else if(maker_name.equals("멍스라이프")){
            Glide.with(context).load(R.drawable.hotel).fitCenter().into(map_img);
            text_map_flag.setText("경기도 용인시 기흥구 청덕동 534-2");
            text_map_call.setText("031-281-5004");
            text_map_time.setText("오전 10:00~오후 7:00");
            text_map_open.setText("정보가 없습니다");
            call_status = true;
        }
        else if(maker_name.equals("퍼피캐슬")){
            Glide.with(context).load(R.drawable.hotel).fitCenter().into(map_img);
            text_map_flag.setText("경기도 용인시 처인구 이동면 송전리 265");
            text_map_call.setText("010-5898-1102");
            text_map_time.setText("오전 9:00~오후 8:00");
            text_map_open.setText("정보가 없습니다");
            call_status = true;
        }


        //동물병원
        else if(maker_name.equals("윤신근애견종합병원")){
            Glide.with(context).load(R.drawable.hospital).fitCenter().into(map_img);
            text_map_flag.setText("서울특별시 중구 필동2가 53");
            text_map_call.setText("02-2272-1234");
            text_map_time.setText("정보가 없습니다");
            text_map_open.setText("정보가 없습니다");
            call_status =true;
        }
        else if(maker_name.equals("동진애견병원")){
            Glide.with(context).load(R.drawable.hospital).fitCenter().into(map_img);
            text_map_flag.setText("경기도 남양주시 화도읍 마석우리 271-3");
            text_map_call.setText("031-594-3119");
            text_map_time.setText("정보가 없습니다");
            text_map_open.setText("정보가 없습니다");
            call_status =true;
        }
        else if(maker_name.equals("고려애견종합병원")){
            Glide.with(context).load(R.drawable.hospital).fitCenter().into(map_img);
            text_map_flag.setText("경기도 성남시 수정구 신흥3동 3777");
            text_map_call.setText("031-735-1942");
            text_map_time.setText("정보가 없습니다");
            text_map_open.setText("정보가 없습니다");
            call_status =true;
        }
        else if(maker_name.equals("24시 넬 동물의료센터")){
            Glide.with(context).load(R.drawable.hospital).fitCenter().into(map_img);
            text_map_flag.setText("경기도 안양시 동안구 평촌동 시민대로 312");
            text_map_call.setText("031-421-7579");
            text_map_time.setText("24시간 영업");
            text_map_open.setText("정보가 없습니다");
            call_status =true;
        }
        else if(maker_name.equals("대학동물병원 수지")){
            Glide.with(context).load(R.drawable.hospital).fitCenter().into(map_img);
            text_map_flag.setText("경기도 용인시 수지구 풍덕천1동 666-1");
            text_map_call.setText("031-261-8277");
            text_map_time.setText("정보가 없습니다");
            text_map_open.setText("정보가 없습니다");
            call_status =true;
        }
        else if(maker_name.equals("아프리카동물병원")){
            Glide.with(context).load(R.drawable.hospital).fitCenter().into(map_img);
            text_map_flag.setText("인천광역시 부평구 부개동 장제로 224-1 1층");
            text_map_call.setText("031-261-8277");
            text_map_time.setText("월~목 : 오전 9:00~오후 10:00\n토~일 : 오전 9:00~오후 6:00");
            text_map_open.setText("https://blog.naver.com/africa7582");
            call_status =true;
            open_status=true;
        }
        else if(maker_name.equals("수원펫동물병원")){
            Glide.with(context).load(R.drawable.hospital).fitCenter().into(map_img);
            text_map_flag.setText("경기도 수원시 권선구 권선동 1058-4");
            text_map_call.setText("031-234-8275");
            text_map_time.setText("정보가 없습니다");
            text_map_open.setText("정보가 없습니다");
            call_status =true;
        }
        else if(maker_name.equals("위니펫 동물병원")){
            Glide.with(context).load(R.drawable.hospital).fitCenter().into(map_img);
            text_map_flag.setText("경기도 화성시 진안동 540-4");
            text_map_call.setText("031-225-2513");
            text_map_time.setText("정보가 없습니다");
            text_map_open.setText("정보가 없습니다");
            call_status =true;
        }
        else if(maker_name.equals("강남종합동물병원 기흥")){
            Glide.with(context).load(R.drawable.hospital).fitCenter().into(map_img);
            text_map_flag.setText("경기도 용인시 기흥구 신갈동 38-5");
            text_map_call.setText("031-283-0775");
            text_map_time.setText("정보가 없습니다");
            text_map_open.setText("정보가 없습니다");
            call_status =true;
        }
        else if(maker_name.equals("강남동물병원 강남구")){
            Glide.with(context).load(R.drawable.hospital).fitCenter().into(map_img);
            text_map_flag.setText("서울특별시 강남구 논현2동 봉은사로 205");
            text_map_call.setText("02-514-7582");
            text_map_time.setText("정보가 없습니다");
            text_map_open.setText("정보가 없습니다");
            call_status =true;
        }
        else if(maker_name.equals("쿨펫동물병원 강서구")){
            Glide.with(context).load(R.drawable.hospital).fitCenter().into(map_img);
            text_map_flag.setText("서울특별시 강서구 가양3동");
            text_map_call.setText("02-3665-7588");
            text_map_time.setText("오전 10:00~오후 8:00");
            text_map_open.setText("정보가 없습니다");
            call_status =true;
        }
        else if(maker_name.equals("청담24시동물병원 수지구")){
            Glide.with(context).load(R.drawable.hospital).fitCenter().into(map_img);
            text_map_flag.setText("경기도 용인시 수지구 죽전동 1254-1");
            text_map_call.setText("031-264-7502");
            text_map_time.setText("정보가 없습니다");
            text_map_open.setText("정보가 없습니다");
            call_status =true;
        }
        else if(maker_name.equals("서울동물메디컬센터")){
            Glide.with(context).load(R.drawable.hospital).fitCenter().into(map_img);
            text_map_flag.setText("서울특별시 강동구 둔촌동 486-7");
            text_map_call.setText("02-457-0075");
            text_map_time.setText("정보가 없습니다");
            text_map_open.setText("정보가 없습니다");
            call_status =true;
        }
        else if(maker_name.equals("나라동물병원 분당구")){
            Glide.with(context).load(R.drawable.hospital).fitCenter().into(map_img);
            text_map_flag.setText("경기도 성남시 분당구 구미동 205-1");
            text_map_call.setText("031-712-0707");
            text_map_time.setText("정보가 없습니다");
            text_map_open.setText("정보가 없습니다");
            call_status =true;
        }
        else if(maker_name.equals("애플펫동물병원")){
            Glide.with(context).load(R.drawable.hospital).fitCenter().into(map_img);
            text_map_flag.setText("경기도 광주시 오포읍 신현리 709-27");
            text_map_call.setText("031-726-4800");
            text_map_time.setText("정보가 없습니다");
            text_map_open.setText("https://applepet.modoo.at/");
            call_status =true;
            open_status =true;
        }
        else if(maker_name.equals("동백동물병원")){
            Glide.with(context).load(R.drawable.hospital).fitCenter().into(map_img);
            text_map_flag.setText("경기도 용인시 기흥구 동백동");
            text_map_call.setText("031-693-8627");
            text_map_time.setText("정보가 없습니다");
            text_map_open.setText("정보가 없습니다");
            call_status =true;
        }
        else if(maker_name.equals("호수동물병원")){
            Glide.with(context).load(R.drawable.hospital).fitCenter().into(map_img);
            text_map_flag.setText("경기도 용인시 기흥구 동백동 607-11");
            text_map_call.setText("031-8005-7507");
            text_map_time.setText("정보가 없습니다");
            text_map_open.setText("정보가 없습니다");
            call_status =true;
        }
        else if(maker_name.equals("윤종합동물병원")){
            Glide.with(context).load(R.drawable.hospital).fitCenter().into(map_img);
            text_map_flag.setText("경기도 용인시 처인구 김량장동 142-11");
            text_map_call.setText("031-335-6318");
            text_map_time.setText("정보가 없습니다");
            text_map_open.setText("정보가 없습니다");
            call_status =true;
        }
        else if(maker_name.equals("애견종합병원 처인구")){
            Glide.with(context).load(R.drawable.hospital).fitCenter().into(map_img);
            text_map_flag.setText("경기도 용인시 처인구 김량장동 333-2");
            text_map_call.setText("031-335-8275");
            text_map_time.setText("정보가 없습니다");
            text_map_open.setText("정보가 없습니다");
            call_status =true;
        }
        else if(maker_name.equals("페티앙동물병원")){
            Glide.with(context).load(R.drawable.hospital).fitCenter().into(map_img);
            text_map_flag.setText("경기도 용인시 처인구 남동 71-1");
            text_map_call.setText("031-335-7578");
            text_map_time.setText("정보가 없습니다");
            text_map_open.setText("정보가 없습니다");
            call_status =true;
        }
        else if(maker_name.equals("용인센트럴동물병원")){
            Glide.with(context).load(R.drawable.hospital).fitCenter().into(map_img);
            text_map_flag.setText("경기도 용인시 처인구 김량장동 12-8");
            text_map_call.setText("031-321-7510");
            text_map_time.setText("정보가 없습니다");
            text_map_open.setText("정보가 없습니다");
            call_status =true;
        }
        else if(maker_name.equals("보라동물병원 기흥구")){
            Glide.with(context).load(R.drawable.hospital).fitCenter().into(map_img);
            text_map_flag.setText("경기도 용인시 기흥구 보라동 417-1");
            text_map_call.setText("031-893-6119");
            text_map_time.setText("정보가 없습니다");
            text_map_open.setText("정보가 없습니다");
            call_status =true;
        }
        else if(maker_name.equals("현대동물병원 기흥구")){
            Glide.with(context).load(R.drawable.hospital).fitCenter().into(map_img);
            text_map_flag.setText("경기도 용인시 기흥구 상갈동 102-12");
            text_map_call.setText("031-285-6334");
            text_map_time.setText("정보가 없습니다");
            text_map_open.setText("정보가 없습니다");
            call_status =true;
        }
        else if(maker_name.equals("우리동네 동물병원")){
            Glide.with(context).load(R.drawable.hospital).fitCenter().into(map_img);
            text_map_flag.setText("경기도 용인시 기흥구 신갈동 722");
            text_map_call.setText("031-281-0811");
            text_map_time.setText("월~금 : 오전 10:00~오후 8:00\n토요일 : 오전 10:00~오후 4:00(일 휴무)");
            text_map_open.setText("정보가 없습니다");
            call_status =true;
        }
        else if(maker_name.equals("탑동물병원 기흥구")){
            Glide.with(context).load(R.drawable.hospital).fitCenter().into(map_img);
            text_map_flag.setText("경기도 용인시 기흥구 언남동 361");
            text_map_call.setText("031-285-7504");
            text_map_time.setText("정보가 없습니다");
            text_map_open.setText("정보가 없습니다");
            call_status =true;
        }
        else if(maker_name.equals("구성종합동물병원")){
            Glide.with(context).load(R.drawable.hospital).fitCenter().into(map_img);
            text_map_flag.setText("경기도 용인시 기흥구 마북동 317-21");
            text_map_call.setText("031-283-8556");
            text_map_time.setText("정보가 없습니다");
            text_map_open.setText("정보가 없습니다");
            call_status =true;
        }
        else if(maker_name.equals("어정동물병원")){
            Glide.with(context).load(R.drawable.hospital).fitCenter().into(map_img);
            text_map_flag.setText("경기도 용인시 기흥구 중동 760-1");
            text_map_call.setText("031-284-9200");
            text_map_time.setText("정보가 없습니다");
            text_map_open.setText("정보가 없습니다");
            call_status =true;
        }
        else if(maker_name.equals("리치펫 동물병원")){
            Glide.with(context).load(R.drawable.hospital).fitCenter().into(map_img);
            text_map_flag.setText("경기도 용인시 기흥구 중동 852-2");
            text_map_call.setText("031-8005-9725");
            text_map_time.setText("정보가 없습니다");
            text_map_open.setText("정보가 없습니다");
            call_status =true;
        }
        else if(maker_name.equals("강남종합동물병원 송파구")){
            Glide.with(context).load(R.drawable.hospital).fitCenter().into(map_img);
            text_map_flag.setText("서울특별시 송파구 송파1동 135-8");
            text_map_call.setText("02-415-0195");
            text_map_time.setText("정보가 없습니다");
            text_map_open.setText("정보가 없습니다");
            call_status =true;
        }
        else if(maker_name.equals("영통동물병원")){
            Glide.with(context).load(R.drawable.hospital).fitCenter().into(map_img);
            text_map_flag.setText("경기도 수원시 영통구 영통동 1000-9");
            text_map_call.setText("031-204-0075");
            text_map_time.setText("정보가 없습니다");
            text_map_open.setText("정보가 없습니다");
            call_status =true;
        }
        else if(maker_name.equals("도그플러스동물병원 강서구")){
            Glide.with(context).load(R.drawable.hospital).fitCenter().into(map_img);
            text_map_flag.setText("서울특별시 강서구 등촌1동 639-11");
            text_map_call.setText("02-3662-3533");
            text_map_time.setText("오전 10:00~오후 10:00");
            text_map_open.setText("https://blog.naver.com/dogplus3533");
            open_status=true;
            call_status =true;
        }
        else if(maker_name.equals("행복나눔동물병원")){
            Glide.with(context).load(R.drawable.hospital).fitCenter().into(map_img);
            text_map_flag.setText("경기도 군포시 당동 904");
            text_map_call.setText("031-398-9975");
            text_map_time.setText("정보가 없습니다");
            text_map_open.setText("정보가 없습니다");
            call_status =true;
        }
        else if(maker_name.equals("다나동물병원 동작구")){
            Glide.with(context).load(R.drawable.hospital).fitCenter().into(map_img);
            text_map_flag.setText("서울특별시 동작구 사당동 1019-54");
            text_map_call.setText("02-3471-0375");
            text_map_time.setText("정보가 없습니다");
            text_map_open.setText("http://jnthome.co.kr/");
            open_status=true;
            call_status=true;
        }
        else if(maker_name.equals("참사랑동물병원 양천구")){
            Glide.with(context).load(R.drawable.hospital).fitCenter().into(map_img);
            text_map_flag.setText("서울특별시 양천구 신정7동 337-7");
            text_map_call.setText("02-2643-7882");
            text_map_time.setText("정보가 없습니다");
            text_map_open.setText("정보가 없습니다");
            call_status=true;
        }
        else if(maker_name.equals("우리동물병원 금천구")){
            Glide.with(context).load(R.drawable.hospital).fitCenter().into(map_img);
            text_map_flag.setText("서울특별시 금천구 독산동 961");
            text_map_call.setText("02-853-7582");
            text_map_time.setText("정보가 없습니다");
            text_map_open.setText("정보가 없습니다");
            call_status=true;
        }
        else if(maker_name.equals("서울대학교 수의과 동물병원")){
            Glide.with(context).load(R.drawable.hospital).fitCenter().into(map_img);
            text_map_flag.setText("서울특별시 관악구 신림동 관악로 1");
            text_map_call.setText("02-880-8661");
            text_map_time.setText("정보가 없습니다");
            text_map_open.setText("http://vmth.snu.ac.kr/");
            open_status=true;
            call_status=true;
        }
        else if(maker_name.equals("내방동물병원")){
            Glide.with(context).load(R.drawable.hospital).fitCenter().into(map_img);
            text_map_flag.setText("서울특별시 서초구 방배동 898-3");
            text_map_call.setText("02-3473-8275");
            text_map_time.setText("오전 9:00~오후 10:00");
            text_map_open.setText("http://blog.kbmyshop.com/content/blog.php?st_idx=102255&st_type=MY");
            call_status=true;
            open_status=true;
        }
        else if(maker_name.equals("도곡종합동물병원")){
            Glide.with(context).load(R.drawable.hospital).fitCenter().into(map_img);
            text_map_flag.setText("서울특별시 강남구 역삼2동");
            text_map_call.setText("02-554-8875");
            text_map_time.setText("오전 10:00~오후 10:00");
            text_map_open.setText("https://blog.naver.com/scarface928");
            call_status=true;
            open_status=true;
        }
        else if(maker_name.equals("성심동물의료센터")){
            Glide.with(context).load(R.drawable.hospital).fitCenter().into(map_img);
            text_map_flag.setText("충청남도 서산시 읍내동 71-13");
            text_map_call.setText("041-664-0607");
            text_map_time.setText("오전 9:00~오후 5:00(일 휴무)");
            text_map_open.setText("http://www.catdog.co.kr/");
            call_status=true;
            open_status=true;
        }
        else if(maker_name.equals("고려동물병원 화성시")){
            Glide.with(context).load(R.drawable.hospital).fitCenter().into(map_img);
            text_map_flag.setText("경기도 화성시 봉담읍 와우리 148-2");
            text_map_call.setText("031-225-0275");
            text_map_time.setText("정보가 없습니다");
            text_map_open.setText("정보가 없습니다");
            call_status=true;
        }
        else if(maker_name.equals("로얄동물메디컬센터 중랑구")){
            Glide.with(context).load(R.drawable.hospital).fitCenter().into(map_img);
            text_map_flag.setText("서울특별시 중랑구 중화2동 207");
            text_map_call.setText("02-494-7580");
            text_map_time.setText("정보가 없습니다");
            text_map_open.setText("http://royalamc.com/");
            call_status=true;
        }
        else if(maker_name.equals("신화동물병원")){
            Glide.with(context).load(R.drawable.hospital).fitCenter().into(map_img);
            text_map_flag.setText("충청남도 서산시 동문동 1012-24");
            text_map_call.setText("041-667-1663");
            text_map_time.setText("정보가 없습니다");
            text_map_open.setText("정보가 없습니다");
            call_status=true;
        }
        else if(maker_name.equals("서산동물병원")){
            Glide.with(context).load(R.drawable.hospital).fitCenter().into(map_img);
            text_map_flag.setText("충청남도 서산시 동문동 1024-6");
            text_map_call.setText("041-667-5525");
            text_map_time.setText("정보가 없습니다");
            text_map_open.setText("http://m.wooreepet.co.kr/html/sub06.html");
            call_status=true;
        }
        else if(maker_name.equals("우리동물병원 성남시")){
            Glide.with(context).load(R.drawable.hospital).fitCenter().into(map_img);
            text_map_flag.setText("경기도 성남시 분당구 정자동 56-1");
            text_map_call.setText("031-719-5725");
            text_map_time.setText("정보가 없습니다");
            text_map_open.setText("정보가 없습니다");
            call_status=true;
        }
        else if(maker_name.equals("김신환동물병원")){
            Glide.with(context).load(R.drawable.hospital).fitCenter().into(map_img);
            text_map_flag.setText("충청남도 서산시 동문동 293-11");
            text_map_call.setText("041-664-0599");
            text_map_time.setText("정보가 없습니다");
            text_map_open.setText("정보가 없습니다");
            call_status=true;
        }
        else if(maker_name.equals("여한경동물병원")){
            Glide.with(context).load(R.drawable.hospital).fitCenter().into(map_img);
            text_map_flag.setText("충청남도 서산시 동문동 309-3");
            text_map_call.setText("041-664-7767");
            text_map_time.setText("정보가 없습니다");
            text_map_open.setText("정보가 없습니다");
            call_status=true;
        }
        else if(maker_name.equals("태안동물병원")){
            Glide.with(context).load(R.drawable.hospital).fitCenter().into(map_img);
            text_map_flag.setText("충청남도 태안군 태안읍 남문리 713-1");
            text_map_call.setText("041-675-8275");
            text_map_time.setText("정보가 없습니다");
            text_map_open.setText("정보가 없습니다");
            call_status=true;
        }
        else if(maker_name.equals("라온반려동물병원")){
            Glide.with(context).load(R.drawable.hospital).fitCenter().into(map_img);
            text_map_flag.setText("충청남도 태안군 태안읍 동문리 342-3");
            text_map_call.setText("041-675-4675");
            text_map_time.setText("정보가 없습니다");
            text_map_open.setText("정보가 없습니다");
            call_status=true;
        }
        else if(maker_name.equals("최가축병원")){
            Glide.with(context).load(R.drawable.hospital).fitCenter().into(map_img);
            text_map_flag.setText("충청남도 태안군 태안읍 남문리 504-11");
            text_map_call.setText("041-672-0346");
            text_map_time.setText("정보가 없습니다");
            text_map_open.setText("정보가 없습니다");
            call_status=true;
        }
        else if(maker_name.equals("현대동물병원 권선구")){
            Glide.with(context).load(R.drawable.hospital).fitCenter().into(map_img);
            text_map_flag.setText("경기도 수원시 권선구 고색동 408-16");
            text_map_call.setText("031-293-0285");
            text_map_time.setText("정보가 없습니다");
            text_map_open.setText("정보가 없습니다");
            call_status=true;
        }
        else if(maker_name.equals("보령동물병원")){
            Glide.with(context).load(R.drawable.hospital).fitCenter().into(map_img);
            text_map_flag.setText("충청남도 보령시 동대동 1701 KR");
            text_map_call.setText("041-934-0013");
            text_map_time.setText("정보가 없습니다");
            text_map_open.setText("정보가 없습니다");
            call_status=true;
        }
        else if(maker_name.equals("훈동물병원 부산")){
            Glide.with(context).load(R.drawable.hospital).fitCenter().into(map_img);
            text_map_flag.setText("부산광역시 동래구 명륜동 20번지 676");
            text_map_call.setText("051-554-0010");
            text_map_time.setText("월~금 : 오전 10:00~오후 7:00\n토 : 오전 10:00~오후 5:00(일 휴무)");
            text_map_open.setText("https://blog.naver.com/hoonah2014");
            call_status=true;
            open_status=true;
        }
        else if(maker_name.equals("김동물병원")){
            Glide.with(context).load(R.drawable.hospital).fitCenter().into(map_img);
            text_map_flag.setText("충청남도 보령시 대천동 434-6");
            text_map_call.setText("041-936-4027");
            text_map_time.setText("정보가 없습니다");
            text_map_open.setText("정보가 없습니다");
            call_status=true;
        }
        else if(maker_name.equals("보령제일동물병원")){
            Glide.with(context).load(R.drawable.hospital).fitCenter().into(map_img);
            text_map_flag.setText("충청남도 보령시 대천동 344-9");
            text_map_call.setText("041-936-7582");
            text_map_time.setText("정보가 없습니다");
            text_map_open.setText("정보가 없습니다");
            call_status=true;
        }
        else if(maker_name.equals("서해가축병원 서천군")){
            Glide.with(context).load(R.drawable.hospital).fitCenter().into(map_img);
            text_map_flag.setText("충청남도 서천군 서천읍 군사리 862-10");
            text_map_call.setText("041-953-0999");
            text_map_time.setText("정보가 없습니다");
            text_map_open.setText("정보가 없습니다");
            call_status=true;
        }
        else if(maker_name.equals("한라동물병원 제주시")){
            Glide.with(context).load(R.drawable.hospital).fitCenter().into(map_img);
            text_map_flag.setText("제주특별자치도 제주시 일도2동 321-10");
            text_map_call.setText("064-753-0880");
            text_map_time.setText("정보가 없습니다");
            text_map_open.setText("정보가 없습니다");
            call_status=true;
        }
        else if(maker_name.equals("백동물병원 영동군")){
            Glide.with(context).load(R.drawable.hospital).fitCenter().into(map_img);
            text_map_flag.setText("충청북도 영동군 영동읍 계산로 27");
            text_map_call.setText("043-744-8275");
            text_map_time.setText("월~금 : 오전 9:30~오후 8:00\n토 : 오전 9:30~오후 4:00(일 휴무)");
            text_map_open.setText("정보가 없습니다");
            call_status=true;
        }
        else if(maker_name.equals("녹십자동물병원")){
            Glide.with(context).load(R.drawable.hospital).fitCenter().into(map_img);
            text_map_flag.setText("충청남도 부여군 부여읍 구아리 141-5");
            text_map_call.setText("041-832-2496");
            text_map_time.setText("정보가 없습니다");
            text_map_open.setText("정보가 없습니다");
            call_status=true;
        }

        else{
            Glide.with(context).load(R.drawable.mainimg).fitCenter().into(map_img);
            text_map_flag.setText("정보가 없습니다");
            text_map_call.setText("정보가 없습니다");
            text_map_open.setText("정보가 없습니다");
            text_map_time.setText("정보가 없습니다");

        }

        tel = "tel:" + text_map_call.getText().toString();
        text_map_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(call_status==true) {
//                Toast.makeText(context,"전화", Toast.LENGTH_SHORT).show();
                    context.startActivity(new Intent("android.intent.action.DIAL", Uri.parse(tel)));
                }
                else{
                    Toast.makeText(context,"전화번호 정보가 없습니다.", Toast.LENGTH_SHORT).show();
                }
            }
        });


        map_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(open_status==true) {
                    Intent intent = new Intent(context, CafeWeb_View.class);
                    intent.putExtra("url", text_map_open.getText().toString());
                    context.startActivity(intent);
                }
                else{
                    Toast.makeText(context,"자세한 정보가 없습니다", Toast.LENGTH_SHORT).show();
                }

            }
        });


        text_map_open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(open_status==true) {
                    Intent intent = new Intent(context, CafeWeb_View.class);
                    intent.putExtra("url", text_map_open.getText().toString());
                    context.startActivity(intent);
                }
                else{
                    Toast.makeText(context,"자세한 정보가 없습니다", Toast.LENGTH_SHORT).show();
                }
//                Toast.makeText(context,"홈페이지", Toast.LENGTH_SHORT).show();
            }
        });




        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(context, "취소 했습니다.", Toast.LENGTH_SHORT).show();
                // 커스텀 다이얼로그를 종료한다.
                dlg.dismiss();

            }
        });
    }

}
