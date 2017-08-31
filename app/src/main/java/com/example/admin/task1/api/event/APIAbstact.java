package com.example.admin.task1.api.event;

import com.example.admin.task1.api.remote.APIInterface;
import com.example.admin.task1.api.util.APIUtil;

/**
 * Created by Admin on 8/28/2017.
 */

public class APIAbstact {
    static APIInterface sApiInterface = APIUtil.getAPI();

}
