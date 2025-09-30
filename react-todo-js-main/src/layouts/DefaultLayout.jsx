const DefaultLayout = ({ children }) => {

    return (
        <div className='w-full h-full overflow-y-scroll bg-slate-400'>
            <div className='max-w-xl mx-auto min-w-[20rem]'>
                { children }
            </div>
        </div>
    )
}

export default DefaultLayout;