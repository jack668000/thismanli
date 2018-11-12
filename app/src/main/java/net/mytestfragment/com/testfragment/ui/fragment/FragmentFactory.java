package net.mytestfragment.com.testfragment.ui.fragment;

/**
 * @创建者 CSDN_LQR
 * @描述 主界面4个Fragment工厂
 */
public class FragmentFactory {

    static FragmentFactory mInstance;

    private FragmentFactory() {
    }

    public static FragmentFactory getInstance() {
        if (mInstance == null) {
            synchronized (FragmentFactory.class) {
                if (mInstance == null) {
                    mInstance = new FragmentFactory();
                }
            }
        }
        return mInstance;
    }

    private FragmentIndex fragmentIndex;
    private FragmentContent fragmentContent;
    private FragmentMessage fragmentMessage;


    public FragmentIndex getIndexFragment() {
        if (fragmentIndex == null) {
            synchronized (FragmentFactory.class) {
                if (fragmentIndex == null) {
                    fragmentIndex = new FragmentIndex();
                }
            }
        }
        return fragmentIndex;
    }

    public FragmentContent getContentpFragment() {
        if (fragmentContent == null) {
            synchronized (FragmentFactory.class) {
                if (fragmentContent == null) {
                    fragmentContent = new FragmentContent();
                }
            }
        }
        return fragmentContent;
    }
    public FragmentMessage getMessageFragment() {
        if (fragmentMessage == null) {
            synchronized (FragmentFactory.class) {
                if (fragmentMessage == null) {
                    fragmentMessage = new FragmentMessage();
                }
            }
        }
        return fragmentMessage;
    }

}
