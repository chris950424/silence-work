package com.silence.gateway.infrastructure.util.route;

import java.io.Serializable;

/**
 *  GrayscaleProperties
 * 
 * @author leo
 * @version 1.1.0
 * @date 2022/2/12
 */
public class GrayscaleProperties implements Serializable {
    private String version;
    private String serverName;
    private String serverGroup;
    private String active;
    private double weight = 1.0D;
}
