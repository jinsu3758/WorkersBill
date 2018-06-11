package com.example.jinsu.work2.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kasum on 2018-06-03.
 */

// 나중에 서버에서 가져온 데이터를 JSON Convert를 통해 변경합니다. 변수명 대소문자는 서버와 일치시키는게 좋습니다.
public class ContractWork {
    private List<WorkTime> workTime;
    private List<RestTime> restTime;

    public ContractWork() {
        workTime = new ArrayList<>();
        restTime = new ArrayList<>();
    }

    public List<WorkTime> getWorkTime() {
        return workTime;
    }

    public void setWorkTime(List<WorkTime> workTime) {
        this.workTime = workTime;
    }

    public List<RestTime> getRestTime() {
        return restTime;
    }

    public void setRestTime(List<RestTime> restTime) {
        this.restTime = restTime;
    }
}
