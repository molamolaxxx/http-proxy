package com.mola.proxy.reverse;

import com.mola.proxy.core.enums.ReverseTypeEnum;
import com.mola.proxy.core.ext.ExtManager;
import com.mola.proxy.core.server.reverse.ReverseProxyServer;
import com.mola.proxy.core.utils.ConfigQueryUtil;
import com.mola.proxy.core.utils.LogUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Locale;
import java.util.Map;

public class ReverseStarter {


    private static final Logger log = LoggerFactory.getLogger(ReverseStarter.class);

    public static void main(String[] args) {
        LogUtil.debugReject();
        Map<String, String> config = ConfigQueryUtil.getConfig(args);
        ExtManager.setHostMappingExt(new HostMappingExtImpl(args[0]));
        // 配置
        String host = config.getOrDefault("host", "120.27.230.24");
        int port = Integer.parseInt(config.getOrDefault("port", "10433"));
        int channelNum = Integer.parseInt(config.getOrDefault("channelNum", "128"));
        ReverseTypeEnum reverseTypeEnum = ReverseTypeEnum.valueOf(config.getOrDefault("type", "HTTP").toUpperCase(Locale.ROOT));

        ReverseProxyServer reverseProxyServer = new ReverseProxyServer();
        reverseProxyServer.start(host, port, channelNum, reverseTypeEnum);
    }
}