package com.innove.authority.config.shiro;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.shiro.session.mgt.SimpleSession;

import java.io.Serializable;

public class MySimplSession extends SimpleSession implements Serializable {

    @JsonIgnore
    boolean valid;
}
