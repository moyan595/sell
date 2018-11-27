package com.xinyan.sell.service.impl;

import com.xinyan.sell.config.WechatAccountConfig;
import com.xinyan.sell.dto.OrderDTO;
import com.xinyan.sell.service.PushMessage;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * 消息推送业务接口实现
 */
@Slf4j
@Service
public class PushMessageImpl implements PushMessage {

    @Autowired
    private WxMpService wxMpService;

    @Autowired
    private WechatAccountConfig wechatAccountConfig;

    /**
     * 微信模板消息：订单完结
     * @param orderDTO
     */
    @Override
    public void orderStatus(OrderDTO orderDTO) {
        WxMpTemplateMessage templateMessage = new WxMpTemplateMessage();
        //模板ID
        templateMessage.setTemplateId(wechatAccountConfig.getTemplateId().get("orderStatus"));
        //接收者openid
        templateMessage.setToUser(orderDTO.getBuyerOpenid());
        //模板数据
        List<WxMpTemplateData> data = Arrays.asList(
                new WxMpTemplateData("first", "亲，请记得收货。"),
                new WxMpTemplateData("keyword1", "微信点餐"),
                new WxMpTemplateData("keyword2", "18333338888"),
                new WxMpTemplateData("keyword3", orderDTO.getOrderId()),
                new WxMpTemplateData("keyword4", orderDTO.getOrderStatusMessage()),
                new WxMpTemplateData("keyword5", orderDTO.getOrderAmount()+""),
                new WxMpTemplateData("remark", "欢迎再次购买!", "#173177")
        );
        templateMessage.setData(data);

        try {
            wxMpService.getTemplateMsgService().sendTemplateMsg(templateMessage);
        } catch (WxErrorException e) {
            log.error("【微信模板消息】发送失败, {}", e);
        }
    }
}
