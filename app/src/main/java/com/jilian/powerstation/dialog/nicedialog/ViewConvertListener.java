package com.jilian.powerstation.dialog.nicedialog;

import java.io.Serializable;

public interface ViewConvertListener extends Serializable {
    long serialVersionUID = System.currentTimeMillis();

    void convertView(ViewHolder holder, BaseNiceDialog dialog);
}
