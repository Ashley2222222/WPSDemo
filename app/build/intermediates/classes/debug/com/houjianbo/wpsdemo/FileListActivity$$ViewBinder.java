// Generated code from Butter Knife. Do not modify!
package com.houjianbo.wpsdemo;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class FileListActivity$$ViewBinder<T extends com.houjianbo.wpsdemo.FileListActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131361881, "field 'lv'");
    target.lv = finder.castView(view, 2131361881, "field 'lv'");
    view = finder.findRequiredView(source, 2131361883, "field 'btnConfirm'");
    target.btnConfirm = finder.castView(view, 2131361883, "field 'btnConfirm'");
    view = finder.findRequiredView(source, 2131361882, "field 'llConfirm'");
    target.llConfirm = finder.castView(view, 2131361882, "field 'llConfirm'");
  }

  @Override public void unbind(T target) {
    target.lv = null;
    target.btnConfirm = null;
    target.llConfirm = null;
  }
}
