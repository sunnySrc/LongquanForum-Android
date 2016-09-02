
package com.zejian.emotionkeyboard.utils;

import com.appbyme.dev.R;

import java.util.Iterator;
import java.util.Vector;


/**
 * @author : zejian
 * @time : 2016年1月5日 上午11:32:33
 * @email : shinezejian@163.com
 * @description :表情加载类,可自己添加多种表情，分别建立不同的map存放和不同的标志符即可
 */
public class EmotionUtils {

    /**
     * 表情类型标志符
     */
    public static final int EMOTION_CLASSIC_TYPE=0x0001;//经典表情

    /**
     * key-表情文字;
     * value-表情图片资源
     */
    public static Vector<Item> EMPTY_MAP = new Vector<Item>();
    public static Vector<Item> EMOTION_CLASSIC_MAP = new Vector<Item>();

    public static class Item {
        final public String mString;
        final public Integer mInteger;

        public Item(String string, Integer integer) {
            mString = string;
            mInteger = integer;
        }
    }

    private static void init() {
        EMOTION_CLASSIC_MAP.add(new Item("[泪]", R.drawable.mc_forum_face198));
        EMOTION_CLASSIC_MAP.add(new Item("[哈哈]", R.drawable.mc_forum_face234));
        EMOTION_CLASSIC_MAP.add(new Item("[抓狂]", R.drawable.mc_forum_face239));
        EMOTION_CLASSIC_MAP.add(new Item("[嘻嘻]", R.drawable.mc_forum_face233));
        EMOTION_CLASSIC_MAP.add(new Item("[偷笑]", R.drawable.mc_forum_face247));
        EMOTION_CLASSIC_MAP.add(new Item("[怒]", R.drawable.mc_forum_face242));
        EMOTION_CLASSIC_MAP.add(new Item("[鼓掌]", R.drawable.mc_forum_face255));
        EMOTION_CLASSIC_MAP.add(new Item("[心]", R.drawable.mc_forum_face279));
        EMOTION_CLASSIC_MAP.add(new Item("[心碎了]", R.drawable.mc_forum_face280));
        EMOTION_CLASSIC_MAP.add(new Item("[生病]", R.drawable.mc_forum_face258));
        EMOTION_CLASSIC_MAP.add(new Item("[爱你]", R.drawable.mc_forum_face17));
        EMOTION_CLASSIC_MAP.add(new Item("[害羞]", R.drawable.mc_forum_face201));
        EMOTION_CLASSIC_MAP.add(new Item("[馋嘴]", R.drawable.mc_forum_face238));
        EMOTION_CLASSIC_MAP.add(new Item("[可怜]", R.drawable.mc_forum_face268));
        EMOTION_CLASSIC_MAP.add(new Item("[晕]", R.drawable.mc_forum_face7));
        EMOTION_CLASSIC_MAP.add(new Item("[花心]", R.drawable.mc_forum_face254));
        EMOTION_CLASSIC_MAP.add(new Item("[太开心]", R.drawable.mc_forum_face261));
        EMOTION_CLASSIC_MAP.add(new Item("[亲亲]", R.drawable.mc_forum_face259));
        EMOTION_CLASSIC_MAP.add(new Item("[鄙视]", R.drawable.mc_forum_face252));
        EMOTION_CLASSIC_MAP.add(new Item("[呵呵]", R.drawable.mc_forum_face25));
        EMOTION_CLASSIC_MAP.add(new Item("[挖鼻屎]", R.drawable.mc_forum_face253));
        EMOTION_CLASSIC_MAP.add(new Item("[衰]", R.drawable.mc_forum_face6));
        EMOTION_CLASSIC_MAP.add(new Item("[兔子]", R.drawable.mc_forum_rabbit_thumb));
        EMOTION_CLASSIC_MAP.add(new Item("[good]", R.drawable.mc_forum_face100));
        EMOTION_CLASSIC_MAP.add(new Item("[来]", R.drawable.mc_forum_face277));
        EMOTION_CLASSIC_MAP.add(new Item("[威武]", R.drawable.mc_forum_face219));
        EMOTION_CLASSIC_MAP.add(new Item("[围观]", R.drawable.mc_forum_face218));
        EMOTION_CLASSIC_MAP.add(new Item("[萌]", R.drawable.mc_forum_kawayi_thumb));
        EMOTION_CLASSIC_MAP.add(new Item("[送花]", R.drawable.mc_forum_face120));
        EMOTION_CLASSIC_MAP.add(new Item("[囧]", R.drawable.mc_forum_face121));
        EMOTION_CLASSIC_MAP.add(new Item("[酷]", R.drawable.mc_forum_qq_01));
        EMOTION_CLASSIC_MAP.add(new Item("[糗大了]", R.drawable.mc_forum_qq_02));
        EMOTION_CLASSIC_MAP.add(new Item("[撇嘴]", R.drawable.mc_forum_qq_03));
        EMOTION_CLASSIC_MAP.add(new Item("[发呆]", R.drawable.mc_forum_qq_04));
        EMOTION_CLASSIC_MAP.add(new Item("[汗]", R.drawable.mc_forum_qq_05));
        EMOTION_CLASSIC_MAP.add(new Item("[睡]", R.drawable.mc_forum_qq_06));
        EMOTION_CLASSIC_MAP.add(new Item("[吃惊]", R.drawable.mc_forum_qq_11));
        EMOTION_CLASSIC_MAP.add(new Item("[白眼]", R.drawable.mc_forum_qq_12));
        EMOTION_CLASSIC_MAP.add(new Item("[疑问]", R.drawable.mc_forum_qq_13));
        EMOTION_CLASSIC_MAP.add(new Item("[阴险]", R.drawable.mc_forum_qq_14));
        EMOTION_CLASSIC_MAP.add(new Item("[左哼哼]", R.drawable.mc_forum_qq_15));
        EMOTION_CLASSIC_MAP.add(new Item("[右哼哼]", R.drawable.mc_forum_qq_16));
        EMOTION_CLASSIC_MAP.add(new Item("[敲打]", R.drawable.mc_forum_qq_21));
        EMOTION_CLASSIC_MAP.add(new Item("[委屈]", R.drawable.mc_forum_qq_22));
        EMOTION_CLASSIC_MAP.add(new Item("[嘘]", R.drawable.mc_forum_qq_23));
        EMOTION_CLASSIC_MAP.add(new Item("[吐]", R.drawable.mc_forum_qq_24));
        EMOTION_CLASSIC_MAP.add(new Item("[做鬼脸]", R.drawable.mc_forum_qq_25));
        EMOTION_CLASSIC_MAP.add(new Item("[ByeBye]", R.drawable.mc_forum_qq_26));
        EMOTION_CLASSIC_MAP.add(new Item("[要哭了]", R.drawable.mc_forum_qq_31));
        EMOTION_CLASSIC_MAP.add(new Item("[傲慢]", R.drawable.mc_forum_qq_32));
        EMOTION_CLASSIC_MAP.add(new Item("[月亮]", R.drawable.mc_forum_qq_33));
        EMOTION_CLASSIC_MAP.add(new Item("[太阳]", R.drawable.mc_forum_qq_34));
        EMOTION_CLASSIC_MAP.add(new Item("[耶]", R.drawable.mc_forum_qq_35));
        EMOTION_CLASSIC_MAP.add(new Item("[握手]", R.drawable.mc_forum_qq_36));
        EMOTION_CLASSIC_MAP.add(new Item("[ok]", R.drawable.mc_forum_qq_41));
        EMOTION_CLASSIC_MAP.add(new Item("[饭]", R.drawable.mc_forum_qq_43));

        EMOTION_CLASSIC_MAP.add(new Item("[咖啡]", R.drawable.mc_forum_qq_42));
        EMOTION_CLASSIC_MAP.add(new Item("[礼物]", R.drawable.mc_forum_qq_44));
        EMOTION_CLASSIC_MAP.add(new Item("[猪头]", R.drawable.mc_forum_qq_45));
        EMOTION_CLASSIC_MAP.add(new Item("[抱抱]", R.drawable.mc_forum_qq_46));
        EMOTION_CLASSIC_MAP.add(new Item("[赞]", R.drawable.mc_forum_m_01));
        EMOTION_CLASSIC_MAP.add(new Item("[Hold]", R.drawable.mc_forum_m_02));
        EMOTION_CLASSIC_MAP.add(new Item("[神马]", R.drawable.mc_forum_m_03));
        EMOTION_CLASSIC_MAP.add(new Item("[坑爹]", R.drawable.mc_forum_m_04));
        EMOTION_CLASSIC_MAP.add(new Item("[有木有]", R.drawable.mc_forum_m_05));
        EMOTION_CLASSIC_MAP.add(new Item("[谢谢]", R.drawable.mc_forum_m_06));
        EMOTION_CLASSIC_MAP.add(new Item("[蓝心]", R.drawable.mc_forum_m_14));
        EMOTION_CLASSIC_MAP.add(new Item("[外星人]", R.drawable.mc_forum_m_12));
        EMOTION_CLASSIC_MAP.add(new Item("[魔鬼]", R.drawable.mc_forum_m_11));
        EMOTION_CLASSIC_MAP.add(new Item("[紫心]", R.drawable.mc_forum_m_14));
        EMOTION_CLASSIC_MAP.add(new Item("[绿心]", R.drawable.mc_forum_m_16));
        EMOTION_CLASSIC_MAP.add(new Item("[黄心]", R.drawable.mc_forum_m_15));
        EMOTION_CLASSIC_MAP.add(new Item("[音符]", R.drawable.mc_forum_m_21));
        EMOTION_CLASSIC_MAP.add(new Item("[闪烁]", R.drawable.mc_forum_m_22));
        EMOTION_CLASSIC_MAP.add(new Item("[星星]", R.drawable.mc_forum_m_23));
        EMOTION_CLASSIC_MAP.add(new Item("[雨滴]", R.drawable.mc_forum_m_24));
        EMOTION_CLASSIC_MAP.add(new Item("[火焰]", R.drawable.mc_forum_m_25));
        EMOTION_CLASSIC_MAP.add(new Item("[便便]", R.drawable.mc_forum_m_26));
        EMOTION_CLASSIC_MAP.add(new Item("[踩一脚]", R.drawable.mc_forum_m_31));
        EMOTION_CLASSIC_MAP.add(new Item("[下雨]", R.drawable.mc_forum_m_32));
        EMOTION_CLASSIC_MAP.add(new Item("[多云]", R.drawable.mc_forum_m_33));
        EMOTION_CLASSIC_MAP.add(new Item("[闪电]", R.drawable.mc_forum_m_34));
        EMOTION_CLASSIC_MAP.add(new Item("[雪花]", R.drawable.mc_forum_m_35));
        EMOTION_CLASSIC_MAP.add(new Item("[旋风]", R.drawable.mc_forum_m_36));
        EMOTION_CLASSIC_MAP.add(new Item("[包]", R.drawable.mc_forum_m_41));
        EMOTION_CLASSIC_MAP.add(new Item("[房子]", R.drawable.mc_forum_m_42));
        EMOTION_CLASSIC_MAP.add(new Item("[烟花]", R.drawable.mc_forum_m_43));
    }

    /**
     * 根据名称获取当前表情图标R值
     * @param EmotionType 表情类型标志符
     * @param imgName 名称
     * @return
     */
    public static int getImgByName(int EmotionType,String imgName) {
           Integer integer = null;
           switch (EmotionType) {
               case EMOTION_CLASSIC_TYPE:
                Iterator it = EMOTION_CLASSIC_MAP.iterator();
                while (it.hasNext()) {
                    Item item = (Item)it.next();
                    if (imgName.equals(item.mString)) {
                        return item.mInteger;
                    }
                }
               default:
                break;
           }
        return integer == null ? -1 : integer;
    }

    /**
     * 根据类型获取表情数据
     * @param EmotionType
     * @return
     */
    public static Vector<Item> getEmojiMap(int EmotionType){
        Vector<Item> EmojiMap=null;
        switch (EmotionType){
            case EMOTION_CLASSIC_TYPE:
                if (EMOTION_CLASSIC_MAP.size() == 0) {
                    init();
                }
                EmojiMap=EMOTION_CLASSIC_MAP;
                break;
            default:
                EmojiMap=EMPTY_MAP;
                break;
        }
        return EmojiMap;
    }

    public static String[] getEmojiNames(int EmotionType){
        Vector<Item> emojiMap = getEmojiMap(EmotionType);
        final int  len = emojiMap.size();
        final String[] result = new String[len];
        for (int i = 0;i < len; i++) {
            result[i] = emojiMap.get(i).mString;
        }
        return result;
    }
}
