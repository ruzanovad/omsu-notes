package com.ruska112;

import java.util.List;

public interface Executable {
    void execute();

    static int getCountExecutableAndExecute(List<?> list) {
        if (list == null) {
            throw new IllegalArgumentException();
        }
        int result = 0;
        for (Object obj : list) {
            if (obj instanceof Executable) {
                ((Executable) obj).execute();
                result++;
            }
        }
        return result;
    }
}
