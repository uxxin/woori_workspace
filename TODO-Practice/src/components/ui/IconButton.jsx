const IconButton = ({ cy, icon, onClick }) => {
  return (
    <button data-cy={cy} onClick={onClick} className={`w-8 text-xl font-semibold cursor-pointer`}>
        {icon}
    </button>
  )
}

export default IconButton