// Generated code from Butter Knife. Do not modify!
package com.houjianbo.wpsdemo;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class MainActivity$$ViewBinder<T extends com.houjianbo.wpsdemo.MainActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131361880, "field 'openFlieList'");
    target.openFlieList = finder.castView(view, 2131361880, "field 'openFlieList'");
    view = finder.findRequiredView(source, 2131361881, "field 'lv'");
    target.lv = finder.castView(view, 2131361881, "field 'lv'");
  }

  @Override public void unbind(T target) {
    target.openFlieList = null;
    target.lv = null;
  }
}
