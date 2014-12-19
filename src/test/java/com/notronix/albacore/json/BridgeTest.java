package com.notronix.albacore.json;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static com.notronix.albacore.json.Bridge.bridge;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(JUnit4.class)
public class BridgeTest
{
    private String json = "{\"param1\":\"blah\",\"param2\":2}";

    @Test
    public void testBridgeObject()
    {
        assertEquals("Failed to bridge object.", json, bridge(new BridgeObject()));
    }

    @Test
    public void testBridgeStringToObject()
    {
        BridgeObject object = bridge(json, BridgeObject.class);

        assertNotNull("Failed to bridge string to object.", object);
        assertEquals("Failed to bridge string to object correctly.", "blah", object.getParam1());
        assertEquals("Failed to bridge string to object correctly.", new Integer(2), object.getParam2());
    }

    private class BridgeObject
    {
        private String param1 = "blah";
        private Integer param2 = 2;

        private String getParam1()
        {
            return param1;
        }

        private Integer getParam2()
        {
            return param2;
        }
    }
}
