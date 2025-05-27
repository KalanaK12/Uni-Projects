import "./categoryicon.css";
import { TbMeat, TbBread, TbCheese, TbFridge } from "react-icons/tb";
import { LuBanana, LuMilk } from "react-icons/lu";
import { MdLocalDrink } from "react-icons/md";
import { BiSolidWasher } from "react-icons/bi";
import { Link } from "react-router-dom";

// Function to map category names to icons
const getCategoryIcon = (catName) => {
  switch (catName) {
    case "Bakery":
      return <TbBread />;
    case "Dairy":
      return <LuMilk />;
    case "Deli":
      return <TbCheese />;
    case "Drinks":
      return <MdLocalDrink />;
    case "Frozen":
      return <TbFridge />;
    case "Fruit":
      return <LuBanana />;
    case "Household":
      return <BiSolidWasher />;
    case "Meat and Seafood":
      return <TbMeat />;
    default:
      return null; // Return null for unknown categories or add a default icon
  }
};

export default function CategoryIcon(props) {
  return (
    <>
      <div>
        <Link
          to={`/search/${encodeURIComponent(props.icon)}`}
          state={{
            searchTerm: props.icon,
          }}
          className="oval-link"
        >
          <div className="oval">{getCategoryIcon(props.icon)}</div>
        </Link>
        <div className="text-center pt-2">
          <span>{props.icon}</span>
        </div>
      </div>
    </>
  );
}
