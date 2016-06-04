/**
 * @author dawson dong
 */

package com.library.Activity.Fragment;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;

import com.library.Activity.BaseActivity;
import com.library.Code.Stack;


public class FragmentStack<T extends BaseBackStackFragment> {

    private final Stack<T> _FragmentStack;
    private FragmentManager _FragmentManager;
    private BaseActivity _Activity;
    private int _ContentId;

    public FragmentStack(BaseActivity activity, int contentId) {
        _Activity = activity;
        this._FragmentManager = activity.getSupportFragmentManager();
        this._ContentId = contentId;
        this._FragmentStack = new Stack<T>();
    }

    public Stack<T> getStack(){
        return _FragmentStack;
    }

    public boolean addFragment(T t) {
        if (t == null) {
            return false;
        }
        _FragmentStack.push(t);
        FragmentTransaction transaction = _FragmentManager.beginTransaction();
        transaction.add(_ContentId, t).addToBackStack(t.getClass().getSimpleName())
                .commitAllowingStateLoss();
        return true;
    }

    /**
     * 处理Activity的返回事件
     */
    public boolean handlerKey(int keyCode, KeyEvent event) {

        if (!_FragmentStack.isEmpty()) {
            T fragment = _FragmentStack.peek();
            if (fragment != null) {
                return fragment.handlerKey(keyCode, event);
            }
        }

        return false;
    }

    public boolean isEmpty() {
        return _FragmentStack.isEmpty();
    }

    /**
     * 关闭Fragment
     */
    public void finishFragment(int count) throws Exception {

        int fragmentSize = _FragmentStack.size();
        if (fragmentSize > 0) {
            if (count == 1) {
                if (fragmentSize > 0) {
                    _FragmentStack.pop();
                    _FragmentManager.popBackStack();
                }
            } else if (count == -1) {
                _FragmentManager.popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                _FragmentStack.clear();
            } else if (count > 1) {
                int index = fragmentSize - count;
                if (index >= 0) {
                    T fragment = _FragmentStack.get(index);
                    _FragmentManager.popBackStackImmediate(fragment.getClass().getSimpleName(), FragmentManager.POP_BACK_STACK_INCLUSIVE);
                    for (int i = 0; count > i; i++) {
                        if (_FragmentStack.size() > 0)
                            _FragmentStack.pop();
                    }
                } else {
                    T fragment = _FragmentStack.get(0);
                    if (fragment != null)
                        _FragmentManager.popBackStackImmediate(fragment.getClass().getSimpleName(), FragmentManager.POP_BACK_STACK_INCLUSIVE);
                    _FragmentStack.clear();
                }
            }

        } else {
            _FragmentStack.clear();
            _FragmentManager.popBackStack();
        }

        if (_FragmentStack.isEmpty()) {
            _Activity.recurSelf();
        }else{
            _FragmentStack.peek().setUserVisibleHint(true);
        }

    }

    public <T extends BaseBackStackFragment> T getTopFragment() {
        if (!_FragmentStack.isEmpty()) return (T) _FragmentStack.peek();
        return null;
    }


    public void clear() {
        _FragmentStack.clear();
        _FragmentManager = null;
        _Activity = null;
    }
}
