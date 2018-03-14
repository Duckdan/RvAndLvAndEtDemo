package study.com.rvandlvandetdemo.model;

/**
 * Created by Administrator on 2018/3/14.
 */
public class ItemBean {
    private String text;

    public ItemBean(String text) {

        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
