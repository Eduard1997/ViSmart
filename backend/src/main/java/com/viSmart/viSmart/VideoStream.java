package com.viSmart.viSmart;

public class VideoStream {
    private Settings settings;

    /**
     * @AttributeType LiveChat
     * /**
     *  * @AssociationType LiveChat
     *  * @AssociationMultiplicity 1
     *  * /
     */
    private LiveChat liveChat;

    private ScreenShare screenShare;

    public VideoStream(Settings set)
    {
        settings = set;
    }
}
