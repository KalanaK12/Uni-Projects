import { useEffect, useState } from "react";
import { Tab, Tabs } from "react-bootstrap";
import { useNavigate } from "react-router-dom";


export default function CustomTabs(props) {
    const [tabKey, setTabKey] = useState("");
    const navigate = useNavigate();

    useEffect(() => {
      setTabKey("");
      
    }, [props.submitClicked]);
    
    const handleTabClick=(k)=>{
      setTabKey(k);
      // props.setItemSearched(false);
      //redirect link
      navigate('/'+k);
    }

    return (
      <>
      <Tabs
        id="controlled-tab-example"
        activeKey={tabKey}
        onSelect={(k) => handleTabClick(k)}
        // className="mb-0 bg-light"
        justify
        >
        <Tab
          eventKey="shop-products"
          title={<span >Shop Products</span>}
        >
        </Tab>
        <Tab
          eventKey="special-offers"
          title={<span>Special Offers</span>}
        >
        </Tab>
        <Tab
          eventKey="boughtBefore"
          title={<span>Bought Before</span>}
        >
          <div>Bought Before </div>
        </Tab>
      </Tabs>
        
    </>
    )
}