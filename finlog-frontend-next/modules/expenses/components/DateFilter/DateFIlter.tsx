import { PaginationMeta } from '@/common/types/api/ApiResponse'
import { getMaxLocalDate } from '@/common/utils/getMaxLocalDate'
import ArrowForwardIosIcon from '@mui/icons-material/ArrowForwardIos'
import SearchIcon from '@mui/icons-material/Search'
import React from 'react'

interface DateFilterProps {
  setPaginationData: React.Dispatch<
    React.SetStateAction<PaginationMeta | undefined>
  >
  setFilterDates: React.Dispatch<
    React.SetStateAction<{ minDate: string; maxDate: string }>
  >
  minDate: string
  maxDate: string
}

export function DateFilter({
  setFilterDates,
  setPaginationData,
  minDate,
  maxDate,
}: DateFilterProps) {
  const [minDateState, setMinDateState] = React.useState(minDate)
  const [maxDateState, setMaxDateState] = React.useState(maxDate)

  const fetchExpensesByDate = (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault()
    const form = e.currentTarget
    const minDate = form.minDate.value
    const maxDate = form.maxDate.value
    setPaginationData((prev) => ({
      ...prev,
      currentPage: 1,
    }))
    setFilterDates({ minDate, maxDate })
  }

  return (
    <form
      onSubmit={fetchExpensesByDate}
      className="flex gap-2 items-center max-[768px]:w-full max-[655px]:flex-col"
    >
      <input
        className="max-[768px]:w-full rounded-[10px] border border-[#d2d2d2] px-3 py-2 font-poppins text-sm outline-none transition-[border-color,box-shadow] duration-300 placeholder:text-sm placeholder:text-[#636363] placeholder:font-['system-ui'] placeholder:transition-[padding-left] placeholder:duration-300 focus:border-[var(--color-01)] focus:shadow-[0_3px_10px_0_rgba(34,41,47,0.1)] focus:placeholder:pl-1.5 read-only:bg-[#d2d2d2] read-only:opacity-70"
        type="date"
        name="minDate"
        defaultValue={minDateState}
        max={maxDateState}
        onChange={(e) => setMinDateState(e.target.value)}
      />
      <ArrowForwardIosIcon
        className="text-gray-400"
        sx={{
          fontSize: '1rem',
          '@media (max-width: 655px)': {
            display: 'none',
          },
        }}
      />
      <input
        className="max-[768px]:w-full rounded-[10px] border border-[#d2d2d2] px-3 py-2 font-poppins text-sm outline-none transition-[border-color,box-shadow] duration-300 placeholder:text-sm placeholder:text-[#636363] placeholder:font-['system-ui'] placeholder:transition-[padding-left] placeholder:duration-300 focus:border-[var(--color-01)] focus:shadow-[0_3px_10px_0_rgba(34,41,47,0.1)] focus:placeholder:pl-1.5 read-only:bg-[#d2d2d2] read-only:opacity-70"
        type="date"
        name="maxDate"
        defaultValue={maxDateState}
        max={getMaxLocalDate()}
        min={minDateState}
        onChange={(e) => setMaxDateState(e.target.value)}
      />
      <button
        type="submit"
        className="max-[655px]:w-full border flex items-center justify-center text-white hover:cursor-pointer px-3 py-1 rounded-[10px] h-[38px] bg-[var(--color-01)] font-poppins font-semibold transition-colors duration-200 hover:bg-[var(--color-02)]"
      >
        <SearchIcon sx={{ fontSize: '1.25rem' }} />
      </button>
    </form>
  )
}
