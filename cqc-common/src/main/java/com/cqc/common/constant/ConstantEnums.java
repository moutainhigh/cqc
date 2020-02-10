package com.cqc.common.constant;

public class ConstantEnums {

    public enum SmsTmpId {

        /**
         * 注册模板id
         */
        REGISTER(1, "527963"),




        /**
         * 重置google验证器
         */
        RESET_GOOGLE(3, "528133"),
        ;
        private String tmpId;
        private int type;

        SmsTmpId(int type, String tmpId) {
            this.type = type;
            this.tmpId = tmpId;
        }

        public String getTmpId() {
            return tmpId;
        }

        public int getType() {
            return type;
        }

        public String getByType(int type) {
            SmsTmpId[] values = SmsTmpId.values();
            for (SmsTmpId value : values) {
                if (type == value.getType()) {
                    return value.getTmpId();
                }
            }
            return "";
        }
    }
}
