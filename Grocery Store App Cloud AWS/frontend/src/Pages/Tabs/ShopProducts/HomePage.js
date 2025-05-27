import React, { useEffect, useState } from 'react';
import { Col, Container, Form, Row,Tab,Tabs} from "react-bootstrap";
import ShopProducts from './ShopProducts';
import SearchedItems from '../SearchedItems/SearchedItems';
import ProductPage from '../ProductPage/ProductPage';
import { Outlet } from 'react-router-dom';



export default function HomePage(props) {
    const [tabKey, setTabKey] = useState("shopProducts");
    useEffect(() => {
      if (props.itemSearched) {
        setTabKey("");
      }
    }, [props.itemSearched]);

    const handleTabClick=()=>{
      props.setItemSearched(false);
    }

    return (
      <>
      <Tabs
        id="controlled-tab-example"
        activeKey={tabKey}
        onSelect={(k) => setTabKey(k)}
        onClick={handleTabClick}
        className="mb-0 bg-light"
        justify
        >
        <Tab
          eventKey="shopProducts"
          title={<span >Shop Products</span>}
        >
            <ShopProducts/>
        </Tab>

        <Tab
          eventKey="frequentlyBought"
          title={<span>Frequently Bought</span>}
        >
          <div>frequently Bought </div>
          <ProductPage/>
        </Tab>
        <Tab
          eventKey="specials"
          title={<span>Special Offers</span>}
        >
          <div>Specials </div>
        </Tab>
        <Tab
          eventKey="boughtBefore"
          title={<span>Bought Before</span>}
        >
          <div>Bought Before </div>
        </Tab>
      </Tabs>
        <Outlet/>
        {props.itemSearched && <SearchedItems searchTerm={props.searchTerm}/>}
        
    </>
    )
}   