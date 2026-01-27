package com.springsecurity.util;

import java.util.List;
//Bot Protection Relevant
public class TurnstileResponse {

    public boolean success;
    public String challenge_ts;
    public String hostname;
    public List<String> error_codes;
}
