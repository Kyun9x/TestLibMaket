package com.lib.marketplace.util;

import com.lib.marketplace.model.orderonline.DmServiceListOrigin;
import com.lib.marketplace.model.orderonline.DmService;

import java.util.ArrayList;

public class ConverUtil {

    private static final String TAG = "ConverUtil";

    public static DmService convertServiceListToPromotion(DmServiceListOrigin dmServiceListOrigin) {
        DmService dmService = new DmService();
        dmService.setServiceCode(dmServiceListOrigin.getCode());
        dmService.setServiceName(dmServiceListOrigin.getName());
        dmService.setServiceScope(dmServiceListOrigin.getUnitScope());
        dmService.setServiceImage(dmServiceListOrigin.getImage());
        dmService.setQuantity(dmServiceListOrigin.getQuantity());
        dmService.setOrgPrice(dmServiceListOrigin.getUnitPrice());
        dmService.setSalePrice(dmServiceListOrigin.getUnitPrice());
        dmService.setAmount(dmServiceListOrigin.getOriginAmount());
        dmService.setServiceUnit(dmServiceListOrigin.getUnitName());
        dmService.setSKU(dmServiceListOrigin.getSKU());
        dmService.setServiceType(dmServiceListOrigin.getType());
        dmService.setAmountCombo(dmServiceListOrigin.getAmountCombo());
        dmService.setComboId(dmServiceListOrigin.getComboId());
        dmService.setComboDesc(dmServiceListOrigin.getComboDesc());
        dmService.setSupplierUid(dmServiceListOrigin.getSupplierUid());
        dmService.setProductUid(dmServiceListOrigin.getProductUid());
        return dmService;
    }

    public static ArrayList<DmService> convertListService(ArrayList<DmServiceListOrigin> listOrigins) {
        ArrayList<DmService> mDatas = new ArrayList<>();
        if (listOrigins != null) {
            for (DmServiceListOrigin dmServiceListOrigin : listOrigins) {
                mDatas.add(convertServiceListToPromotion(dmServiceListOrigin));
            }
        }
        return mDatas;
    }
}
